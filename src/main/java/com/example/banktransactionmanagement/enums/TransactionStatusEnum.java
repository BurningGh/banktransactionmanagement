package com.example.banktransactionmanagement.enums;

import lombok.Getter;

/**
 * TransactionStatus
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Getter
public enum TransactionStatusEnum {
    /**
     * 待支付
     */
    PENDING(1, "待支付"),

    /**
     * 已支付
     */
    PAID(2, "已支付"),

    /**
     * 支付失败
     *
     */
    PAY_FAILED(3, "支付失败"),

    /**
     * 已取消
     */
    CANCELED(4, "已取消"),

    /**
     * 已退款
     */
    REFUNDED(5, "已退款");

    /**
     * 状态代码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String description;


    TransactionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}