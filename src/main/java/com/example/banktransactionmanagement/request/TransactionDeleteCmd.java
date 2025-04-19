package com.example.banktransactionmanagement.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 交易删除命令
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class TransactionDeleteCmd extends BaseCmd {

    @NotNull(message = "id不能为空")
    private Long id;

    public void validate(){

    }

}