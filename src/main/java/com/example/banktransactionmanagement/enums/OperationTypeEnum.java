package com.example.banktransactionmanagement.enums;

import lombok.Getter;

/**
 * OperationType
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Getter
public enum OperationTypeEnum {
    /**
     * 创建交易
     */
    CREATE_TRANSACTION(1, "创建交易"),
    /**
     * 更新交易
     */
    UPDATE_TRANSACTION(2, "更新交易"),
    /**
     * 删除交易
     */
    DELETE_TRANSACTION(3, "删除交易");

    private final Integer code;
    private final String description;

    OperationTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

}