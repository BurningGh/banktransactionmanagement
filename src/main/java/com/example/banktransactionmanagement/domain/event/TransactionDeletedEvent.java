package com.example.banktransactionmanagement.domain.event;

import com.example.banktransactionmanagement.domain.TransactionDelete;
import org.springframework.context.ApplicationEvent;


public class TransactionDeletedEvent extends ApplicationEvent {


    private static final long serialVersionUID = -7509658415184730994L;

    public TransactionDeletedEvent(TransactionDelete source) {
        super(source);
    }


    public TransactionDelete getCmd() {
        return (TransactionDelete) getSource();
    }
}    