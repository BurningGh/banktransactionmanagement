package com.example.banktransactionmanagement.exception;

import lombok.Getter;
import lombok.Setter;


/**
 * 基础异常
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;


    public BaseException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errCode = errorEnum.getCode();
    }

    public BaseException(ErrorEnum errorEnum, Throwable e) {
        super(errorEnum.getMessage(), e);
        this.errCode = errorEnum.getCode();
    }
    public BaseException(ErrorEnum errorEnum, String errMessage, Throwable e) {
        super(errMessage, e);
        this.errCode = errorEnum.getCode();
    }

    public BaseException(ErrorEnum errorEnum, String errMessage) {
        super(errMessage);
        this.errCode = errorEnum.getCode();
    }

    public BaseException(String errMessage, Throwable e) {
        super(errMessage, e);
    }




}
