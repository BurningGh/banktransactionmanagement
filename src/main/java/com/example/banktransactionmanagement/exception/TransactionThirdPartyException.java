package com.example.banktransactionmanagement.exception;


/**
 * 交易第三方异常，第三方引发的异常，需要用快速反馈给第三方进行处理
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class TransactionThirdPartyException extends BaseException{

    private static final long serialVersionUID = 8300051876007880780L;

    public TransactionThirdPartyException(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public TransactionThirdPartyException(ErrorEnum errorEnum, Throwable e) {
        super(errorEnum, e);
    }

    public TransactionThirdPartyException(ErrorEnum errorEnum, String errMessage, Throwable e) {
        super(errorEnum, errMessage, e);
    }

    public TransactionThirdPartyException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public TransactionThirdPartyException(ErrorEnum errorEnum, String errMessage) {
        super(errorEnum, errMessage);
    }
}