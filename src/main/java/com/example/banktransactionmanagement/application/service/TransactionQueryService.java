package com.example.banktransactionmanagement.application.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.banktransactionmanagement.converter.TransactionConverter;
import com.example.banktransactionmanagement.domain.TransactionDO;
import com.example.banktransactionmanagement.infrastructure.proxy.ProductProxy;
import com.example.banktransactionmanagement.infrastructure.repository.TransactionRepository;
import com.example.banktransactionmanagement.infrastructure.proxy.UserProxy;
import com.example.banktransactionmanagement.response.TransactionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.banktransactionmanagement.domain.TransactionDomainService.TRANSACTIONS_CACHE;

/**
 * 交易查询服务
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Service
@RequiredArgsConstructor
public class TransactionQueryService {

    private final TransactionRepository transactionRepository;

    private final UserProxy userProxy;

    private final ProductProxy productProxy;


    /**
     * 查询交易详情
     *
     * @param id 交易ID
     * @return 交易详情
     */
    @Cacheable(value = TRANSACTIONS_CACHE, key = "#id")
    public TransactionVO getById(Long id) {
        TransactionDO transactionDO = transactionRepository.selectById(id);
        TransactionVO transactionVO = TransactionConverter.INSTANCE.map(transactionDO);
        fillExtra(transactionVO);
        return transactionVO;
    }


    /**
     * 获取所有交易记录
     *
     * @return 交易记录列表
     */
    @Cacheable(value = TRANSACTIONS_CACHE, key =  "'all'")
    public List<TransactionVO> listAll() {
        // 一般要避免listAll的危险操作，这里近因为任务要求使用，实际最好要limit 参数，避免一次查询过多
        List<TransactionDO> transactionDOS = transactionRepository.selectList(Wrappers.lambdaQuery());
        if (transactionDOS.isEmpty()) {
            return Collections.emptyList();
        }
        List<TransactionVO> transactions = TransactionConverter.INSTANCE.maps(transactionDOS);
        fillExtra(transactions);
        return transactions;
    }

    private void fillExtra(TransactionVO transactionVO) {
        if (transactionVO == null) {
            return;
        }
        transactionVO.setBuyerName(userProxy.getUserName(transactionVO.getBuyerId()));
        transactionVO.setProductName(productProxy.getProductName(transactionVO.getProductId()));
    }

    private void fillExtra(List<TransactionVO> transactions) {
        if (transactions.isEmpty()) {
            return;
        }
        Map<Long, String> buyerIdToNameMap = transactions.stream().map(TransactionVO::getBuyerId).filter(Objects::nonNull).distinct()
                .collect(Collectors.toMap(Function.identity(), userProxy::getUserName));
        Map<Long, String> productIdToNameMap = transactions.stream().map(TransactionVO::getProductId).filter(Objects::nonNull).distinct()
                .collect(Collectors.toMap(Function.identity(), productProxy::getProductName));
        transactions.forEach(transactionVO -> {
            transactionVO.setBuyerName(buyerIdToNameMap.get(transactionVO.getBuyerId()));
            transactionVO.setProductName(productIdToNameMap.get(transactionVO.getProductId()));
        });
    }
}    