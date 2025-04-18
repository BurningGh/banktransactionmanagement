package com.example.banktransactionmanagement.domain.event;

import com.example.banktransactionmanagement.domain.TransactionUpdate;
import org.springframework.context.ApplicationEvent;


public class TransactionUpdatedEvent extends ApplicationEvent {


    private static final long serialVersionUID = 1388532270364805521L;

    public TransactionUpdatedEvent(TransactionUpdate source) {
        super(source);
    }

    public TransactionUpdate getCmd() {
        return (TransactionUpdate) getSource();
    }
}    