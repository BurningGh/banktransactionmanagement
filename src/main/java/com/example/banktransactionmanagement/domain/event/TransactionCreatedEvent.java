package com.example.banktransactionmanagement.domain.event;

import com.example.banktransactionmanagement.domain.TransactionDO;
import org.springframework.context.ApplicationEvent;


public class TransactionCreatedEvent extends ApplicationEvent {


    private static final long serialVersionUID = -5947771248113768397L;

    public TransactionCreatedEvent(TransactionDO source) {
        super(source);
    }

    public TransactionDO getCmd() {
        return (TransactionDO) getSource();
    }
}    