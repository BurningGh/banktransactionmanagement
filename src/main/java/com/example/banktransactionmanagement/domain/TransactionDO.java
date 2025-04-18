package com.example.banktransactionmanagement.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.banktransactionmanagement.enums.TransactionStatusEnum;
import com.example.banktransactionmanagement.util.SnowflakeIdGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("transactions")
public class TransactionDO extends BaseEntity {

    /**
     * 交易描述
     */
    private String description;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 购买者ID
     */
    private Long buyerId;

    /**
     * 商品ID，一般是多个商品，这里仅供演示
     */
    private Long productId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 订单状态
     *
     * @see com.example.banktransactionmanagement.enums.TransactionStatusEnum
     */
    private Integer status;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    private String memo;

    public static TransactionDO create(TransactionCreate transactionCreateCmd) {
        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setId(SnowflakeIdGenerator.generateId());
        LocalDateTime now = LocalDateTime.now();
        transactionDO.setDescription(transactionCreateCmd.getDescription());
        transactionDO.setAmount(transactionCreateCmd.getAmount());
        transactionDO.setBuyerId(transactionCreateCmd.getBuyerId());
        transactionDO.setProductId(transactionCreateCmd.getProductId());
        transactionDO.setQuantity(transactionCreateCmd.getQuantity());
        transactionDO.setOrderTime(now);
        transactionDO.setMemo(transactionCreateCmd.getMemo());

        transactionDO.setCreateBy(transactionCreateCmd.getOperator());
        transactionDO.setCreateAt(now);
        // 设置初始状态为待支付
        transactionDO.setStatus(TransactionStatusEnum.PENDING.getCode());
        return transactionDO;
    }

    public void delete(String operator, String memo) {
        super.setDeleted(true);
        super.setUpdateAt(LocalDateTime.now());
        super.setUpdateBy(operator);
        this.memo = memo;
    }

    public void update(String operator, String memo, String description, BigDecimal amount
            , Integer quantity) {
        if (StringUtils.hasText(description)) {
            this.description = description;
        }
        if (StringUtils.hasText(memo)) {
            this.memo = memo;
        }
        if (amount != null) {
            this.amount = amount;
        }
        if (quantity != null) {
            this.quantity = quantity;
        }
        super.setUpdateBy(operator);
        super.setUpdateAt(LocalDateTime.now());
    }

}    