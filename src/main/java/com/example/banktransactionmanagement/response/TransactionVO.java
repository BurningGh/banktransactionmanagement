package com.example.banktransactionmanagement.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.banktransactionmanagement.common.Constant.YYYY_MM_DD_HH_MM_SS;

/**
 * 交易vo
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper =true)
public class TransactionVO extends BaseVO {

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
    @JsonIgnore
    private Long buyerId;
    /**
     * 购买者名称
     */
    private String buyerName;

    /**
     * 商品ID，一般是多个商品，这里仅供演示
     */
    @JsonIgnore
    private Long productId;

    /**
     * 商品名称 一般是多个商品，这里仅供演示
     */
    private String productName;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 订单状态
     * <ul>
     *     <li>1 - 待支付</li>
     *     <li>2 - 已支付</li>
     *     <li>3 - 支付失败</li>
     *     <li>4 - 已取消</li>
     *     <li>5 - 已退款</li>
     * </ul>
     * @see com.example.banktransactionmanagement.enums.TransactionStatusEnum
     */
    private Integer status;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime orderTime;

    /**
     * 支付时间
     */
    @JsonFormat(pattern = YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime paymentTime;

    /**
     * 备注
     */
    private String memo;

}    