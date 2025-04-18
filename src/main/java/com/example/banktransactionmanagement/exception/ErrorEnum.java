package com.example.banktransactionmanagement.exception;

import lombok.Getter;

/**
 * ErrorEnum,code第1位标识错误类型，23位标识具体应用，456位标识具体错误类型，
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Getter
public enum ErrorEnum {
    SUCCESS("000000", "成功"),


    // 系统类故障
    /**
     * 交易:系统异常
     */
    TRANSACTION_SYSTEM_ERROR("101001", "交易:系统异常"),



    // 用户类故障
    /**
     * 用户操作异常
     */
    TRANSACTION_USER_ERROR("201001", "用户操作异常"),

    /**
     * 交易重复发起
     */
    TRANSACTION_REPEATED("201002", "交易重复发起"),

    /**
     * 交易正在处理中
     */
    TRANSACTION_PROCESSING("201003", "交易正在处理中"),

    /**
     * 非待支付的交易无法删除
     */
    TRANSACTION_CAN_NOT_DELETE("201102", "非待支付的交易无法删除"),

    /**
     * 未支付的订单才能修改
     */
    TRANSACTION_CAN_NOT_UPDATE("201103", "未支付的订单才能修改"),

    /**
     * 已支付的订单才能退款
     */
    TRANSACTION_CAN_NOT_REFUND("201104", "已支付的订单才能退款"),

    /**
     * 交易已经取消
     */
    TRANSACTION_CANCELED("201004", "交易已经取消"),

    /**
     * 交易不存在
     */
    TRANSACTION_NOT_EXIST("201004", "交易不存在"),

    REQUEST_REPEATED("201005", "重复请求"),

    PARAM_ERROR("201006", "参数异常"),




    // 第三方故障
    /**
     * 交易:第三方异常
     */
    TRANSACTION_THIRD_PARTY_ERROR("301001", "交易:第三方异常");

    private final String code;
    private final String message;

    /**
     * 构造函数，初始化错误代码和消息
     *
     * @param code 错误代码
     * @param message 错误消息
     */
    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}