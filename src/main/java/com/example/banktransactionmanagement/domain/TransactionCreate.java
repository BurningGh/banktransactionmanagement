package com.example.banktransactionmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 交易创建命令
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreate  {

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
     * 商品ID
     */
    private Long productId;

    /**
     * 购买数量
     */
    private Integer quantity;

    private String operator;

    /**
     * 操作备注
     */
    private String memo;

}