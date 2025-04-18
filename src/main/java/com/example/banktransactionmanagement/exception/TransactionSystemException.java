package com.example.banktransactionmanagement.exception;


/**
 * 交易系统异常，系统产生的异常，需要运维介入处理
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class TransactionSystemException extends BaseException {


    private static final long serialVersionUID = 7568787291564297991L;


    public TransactionSystemException(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public TransactionSystemException(ErrorEnum errorEnum, Throwable e) {
        super(errorEnum, e);
    }

    public TransactionSystemException(ErrorEnum errorEnum, String errMessage, Throwable e) {
        super(errorEnum, errMessage, e);
    }

    public TransactionSystemException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public TransactionSystemException(ErrorEnum errorEnum, String errMessage) {
        super(errorEnum, errMessage);
    }
}