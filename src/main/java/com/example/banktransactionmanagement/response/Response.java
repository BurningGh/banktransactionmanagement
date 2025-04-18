package com.example.banktransactionmanagement.response;

import com.example.banktransactionmanagement.exception.AssertUtil;
import com.example.banktransactionmanagement.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Getter
@Setter
@AllArgsConstructor
public class Response<T> implements Serializable {

    public static final Response<Void> SUCCESS_RESP = new Response<>();


    private static final long serialVersionUID = 8735092027495918710L;

    private boolean success;

    private String code;

    private String message;

    private T content;

    @Override
    public String toString() {
        return "Response{" + "success=" + success + ", code='" + code+ ", content='" + content + ", message='" + message + '}';
    }


    private Response() {
        this.success = true;
        this.code = ErrorEnum.SUCCESS.getCode();
        this.message = ErrorEnum.SUCCESS.getMessage();
    }




    public Response(T content) {
        this.content = content;
        setResponseStatus(ErrorEnum.SUCCESS);
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
        this.success = ErrorEnum.SUCCESS.getCode().equals(code);
    }

    public Response(ErrorEnum errorEnum) {
        setResponseStatus(errorEnum);
    }

    private void setResponseStatus(ErrorEnum errorEnum) {
        AssertUtil.sysTrue(errorEnum != null, ErrorEnum.TRANSACTION_SYSTEM_ERROR, "errorEnum is null");
        this.code=errorEnum.getCode();
        this.message = errorEnum.getMessage();
        this.success = ErrorEnum.SUCCESS.getCode().equals(errorEnum.getCode());
    }


}
