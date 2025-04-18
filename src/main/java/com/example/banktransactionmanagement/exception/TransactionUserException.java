package com.example.banktransactionmanagement.exception;


/**
 * 交易用户异常，用户触发的异常，需要用户参考手册进行处理
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class TransactionUserException extends BaseException{

    private static final long serialVersionUID = 7128069308123764433L;

    public TransactionUserException(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public TransactionUserException(ErrorEnum errorEnum, Throwable e) {
        super(errorEnum, e);
    }

    public TransactionUserException(ErrorEnum errorEnum, String errMessage, Throwable e) {
        super(errorEnum, errMessage, e);
    }

    public TransactionUserException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public TransactionUserException(ErrorEnum errorEnum, String errMessage) {
        super(errorEnum, errMessage);
    }
}