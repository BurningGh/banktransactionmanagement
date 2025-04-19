package com.example.banktransactionmanagement.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 交易创建命令
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class TransactionCreateCmd extends BaseCmd {

    /**
     * 交易描述
     */
    @NotBlank(message = "交易描述不能为空")
    private String description;

    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "交易金额必须大于0")
    private BigDecimal amount;


    /**
     * 购买者ID
     */
    @NotNull(message = "购买者ID不能为空")
    private Long buyerId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @DecimalMin(value = "1", message = "购买数量必须大于0")
    private Integer quantity;

    public void validate(){

    }

}