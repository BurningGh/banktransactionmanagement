package com.example.banktransactionmanagement.domain.event;

import com.example.banktransactionmanagement.domain.AuditLogDO;
import org.springframework.context.ApplicationEvent;


public class AuditLogCreatedEvent extends ApplicationEvent {



    private static final long serialVersionUID = -863908369465912794L;

    public AuditLogCreatedEvent(AuditLogDO source) {
        super(source);
    }

    public AuditLogDO getAuditLog() {
        return (AuditLogDO) getSource();
    }
}    