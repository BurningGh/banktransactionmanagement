package com.example.banktransactionmanagement.domain.event;

import org.springframework.context.ApplicationEvent;


public class BaseEvent extends ApplicationEvent {


    private static final long serialVersionUID = -7509658415184730994L;

    public BaseEvent(Object source) {
        super(source);
    }

}