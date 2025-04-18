package com.example.banktransactionmanagement.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 审计日志实体类
 */
@Data
@TableName("audit_logs")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AuditLogDO {

    @TableId
    private Long id;

    /**
     * 操作对象id
     */
    private Long operationObjectId;

    /**
     * 操作类型
     * @see com.example.banktransactionmanagement.enums.OperationTypeEnum
     */
    private Integer operationType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作内容
     */
    private String operationContent;

    /**
     * 操作描述
     */
    private String operationDescription;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;
}    