package com.example.banktransactionmanagement.listener;

import com.example.banktransactionmanagement.domain.AuditLogDO;
import com.example.banktransactionmanagement.domain.event.AuditLogCreatedEvent;
import com.example.banktransactionmanagement.domain.event.TransactionDeletedEvent;
import com.example.banktransactionmanagement.enums.OperationTypeEnum;
import com.example.banktransactionmanagement.util.JsonUtils;
import com.example.banktransactionmanagement.util.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static com.example.banktransactionmanagement.util.DateUtil.timestampToLocalDateTime;

/**
 * 交易删除监听器
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Component
@Slf4j
@RequiredArgsConstructor

public class TransactionDeletedEventListener {

    private final ApplicationEventPublisher eventPublisher;

    /**
     * 处理事件，这里需要异步执行，因为仅供演示，直接使用String的事件监听器，在服务内处理事件
     *
     * @param event 事件
     */
    @Async
    @EventListener
    public void handleCustomEvent(TransactionDeletedEvent event) {
        //一般是事件和业务一起持久化，然后异步使用事务事件消息发布，避免消息丢失，这里仅供演示
        log.info("Received event: {}", event);
        // 做其他业务
        doOtherBusiness();
        //发布审计记录
        AuditLogDO auditLogDO = buildAuditLogDO(event);
        eventPublisher.publishEvent(new AuditLogCreatedEvent(auditLogDO));
    }

    public void doOtherBusiness(){

    }

    private static AuditLogDO buildAuditLogDO(TransactionDeletedEvent event) {
        return AuditLogDO.of(SnowflakeIdGenerator.generateId(),
                event.getCmd().getId(),
                OperationTypeEnum.DELETE_TRANSACTION.getCode(),
                event.getCmd().getOperator(),
                JsonUtils.toJsonStr(event.getCmd()),
                OperationTypeEnum.DELETE_TRANSACTION.getDescription(),
                timestampToLocalDateTime(event.getTimestamp()));
    }
}
