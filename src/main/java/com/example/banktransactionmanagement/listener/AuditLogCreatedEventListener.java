package com.example.banktransactionmanagement.listener;

import com.example.banktransactionmanagement.domain.event.AuditLogCreatedEvent;
import com.example.banktransactionmanagement.infrastructure.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 审计日志创建事件监听器
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditLogCreatedEventListener {

    private final AuditLogRepository auditLogRepository;

    private final ApplicationEventPublisher eventPublisher;

    /**
     * 处理事件，这里需要异步执行，因为仅供演示，直接使用String的事件监听器，在服务内处理事件
     *
     * @param event 事件
     */
    @Async
    @EventListener
    public void handleCustomEvent(AuditLogCreatedEvent event) {
        //一般是事件和业务一起持久化，然后异步使用事务事件消息发布，避免消息丢失，这里仅供演示
        log.info("Received event: {}", event);
        // 做其他业务
        auditLogRepository.insert(event.getAuditLog());
    }


}
