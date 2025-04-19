package com.example.banktransactionmanagement.request;

import com.example.banktransactionmanagement.exception.AssertUtil;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

import static com.example.banktransactionmanagement.exception.ErrorEnum.TRANSACTION_CAN_NOT_UPDATE;

/**
 * 交易更新命令
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class TransactionUpdateCmd extends BaseCmd {

    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 交易描述
     */
    private String description;

    /**
     * 交易金额
     */
    @DecimalMin(value = "0.0", inclusive = false, message = "交易金额必须大于0")
    private BigDecimal amount;


    /**
     * 购买数量
     */
    @DecimalMin(value = "1", message = "购买数量必须大于0")
    private Integer quantity;

    public void validate(){
        boolean allNull = description==null&&amount==null&&quantity==null &&super.getMemo()==null;
        AssertUtil.userTrue(!allNull,TRANSACTION_CAN_NOT_UPDATE,"交易更新失败:没更新的内容");
    }

}