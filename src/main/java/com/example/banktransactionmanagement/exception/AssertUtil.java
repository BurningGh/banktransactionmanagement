package com.example.banktransactionmanagement.exception;

/**
 * AssertUtil工具类
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class AssertUtil {

    public static void userTrue(boolean expression,ErrorEnum error , String errMessage) {
        if (!expression) {
            throw new TransactionUserException(error, errMessage);
        }
    }

    public static void sysTrue(boolean expression,ErrorEnum error , String errMessage) {
        if (!expression) {
            throw new TransactionSystemException(error, errMessage);
        }
    }

    public static void thirdPartyTrue(boolean expression,ErrorEnum error , String errMessage) {
        if (!expression) {
            throw new TransactionThirdPartyException(error, errMessage);
        }
    }
}