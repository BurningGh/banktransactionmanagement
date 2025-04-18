package com.example.banktransactionmanagement.domain;

import com.example.banktransactionmanagement.aspect.IRedisCmd;
import com.example.banktransactionmanagement.domain.event.TransactionCreatedEvent;
import com.example.banktransactionmanagement.domain.event.TransactionDeletedEvent;
import com.example.banktransactionmanagement.domain.event.TransactionUpdatedEvent;
import com.example.banktransactionmanagement.enums.TransactionStatusEnum;
import com.example.banktransactionmanagement.exception.AssertUtil;
import com.example.banktransactionmanagement.exception.ErrorEnum;
import com.example.banktransactionmanagement.exception.TransactionUserException;
import com.example.banktransactionmanagement.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 交易领域服务
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Service
@RequiredArgsConstructor
public class TransactionDomainService {

    public static final String TRANSACTION_KEY = "transaction:";
    public static final String TRANSACTIONS_CACHE = "transactions";

    private final TransactionRepository transactionRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final IRedisCmd redisCmd;

    @Transactional
    @CacheEvict(value = TRANSACTIONS_CACHE, key =  "'all'")
    public TransactionDO createTransaction(TransactionCreate transactionCreate) {
        TransactionDO transactionDO = TransactionDO.create(transactionCreate);
        // 这里一般需要进行锁定商品库存资源，比如将商品的库存-1，交易中+1，并且设计延时检查，超时未支付需要释放资源，这里仅演示交易的，不写太复杂。
        transactionRepository.insert(transactionDO);
        eventPublisher.publishEvent(new TransactionCreatedEvent(transactionDO));
        return transactionDO;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = TRANSACTIONS_CACHE,key = "#transactionUpdate.id"),
            @CacheEvict(value = TRANSACTIONS_CACHE, key =  "'all'")
    })
    public TransactionDO updateTransaction( TransactionUpdate transactionUpdate) {
        TransactionDO existingTransaction = getById(transactionUpdate.getId());
        validateUpdate(existingTransaction);

        //实际生产交易的锁定，推荐使用redisson的分布式的lock，这里仅演示锁定
        String lockKey = TRANSACTION_KEY +transactionUpdate.getId();
        //锁定资源
        lock(lockKey);
        try {
            existingTransaction.update(transactionUpdate.getOperator(),transactionUpdate.getMemo(),transactionUpdate.getDescription(),transactionUpdate.getAmount(),transactionUpdate.getQuantity());
            transactionRepository.updateById(existingTransaction);
            eventPublisher.publishEvent(new TransactionUpdatedEvent(transactionUpdate));
            return existingTransaction;
        }finally {
            //释放锁
            releaseLock(lockKey);
        }
    }



    @Transactional
    @Caching(evict = {
            @CacheEvict(value = TRANSACTIONS_CACHE,key = "#transactionDelete.id"),
            @CacheEvict(value = TRANSACTIONS_CACHE, key =  "'all'")
    })
    public TransactionDO deleteTransaction(TransactionDelete transactionDelete) {
        TransactionDO existingTransaction = getById(transactionDelete.getId());
        validateDelete(existingTransaction);
        String lockKey = TRANSACTION_KEY +transactionDelete.getId();
        //锁定资源
        lock(lockKey);
        try {
            transactionRepository.deleteById(existingTransaction.getId());
            eventPublisher.publishEvent(new TransactionDeletedEvent(transactionDelete));
            return existingTransaction;
        } finally {
            //释放锁
            releaseLock(lockKey);
        }
    }

    private static void validateUpdate(TransactionDO existingTransaction) {
        AssertUtil.userTrue(existingTransaction != null, ErrorEnum.TRANSACTION_NOT_EXIST, "交易不存在,无法更新");
        AssertUtil.userTrue(TransactionStatusEnum.PENDING.getCode().equals(existingTransaction.getStatus()),ErrorEnum.TRANSACTION_CAN_NOT_UPDATE
                ,ErrorEnum.TRANSACTION_CAN_NOT_UPDATE.getMessage());
    }

    private static void validateDelete(TransactionDO existingTransaction) {
        AssertUtil.userTrue(existingTransaction != null, ErrorEnum.TRANSACTION_NOT_EXIST, "交易不存在,无法删除");
        AssertUtil.userTrue(TransactionStatusEnum.PENDING.getCode().equals(existingTransaction.getStatus()),ErrorEnum.TRANSACTION_CAN_NOT_DELETE
                ,ErrorEnum.TRANSACTION_CAN_NOT_DELETE.getMessage());
    }

    private TransactionDO getById( long id) {
        return transactionRepository.selectById(id);
    }


    private void releaseLock(String lockKey) {
        //释放锁
        redisCmd.del(lockKey);
    }

    private void lock(String lockKey) {
        //这里也看情况可以选择使用redisson的分布式锁，这里仅演示锁定,可以设置等待时间
        boolean lock = redisCmd.setNx(lockKey, 10);
        if(!lock){
            throw new TransactionUserException(ErrorEnum.TRANSACTION_PROCESSING,"交易正在处理中，请稍后再试");
        }
    }

}