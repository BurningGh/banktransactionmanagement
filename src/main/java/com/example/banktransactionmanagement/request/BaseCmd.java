package com.example.banktransactionmanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 基础操作类
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Data
public class BaseCmd {

    /**
     * 请求ID
     */
    @NotNull(message = "请求ID不能为空")
    private Long requestId;

    /**
     * 操作人
     */
    @NotBlank(message = "操作人不能为空")
    private String operator;

    /**
     * 操作备注
     */
    private String memo;

}    