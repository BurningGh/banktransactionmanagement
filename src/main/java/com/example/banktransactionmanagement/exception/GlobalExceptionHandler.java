package com.example.banktransactionmanagement.exception;

import com.example.banktransactionmanagement.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.example.banktransactionmanagement.exception.ErrorEnum.PARAM_ERROR;
import static com.example.banktransactionmanagement.exception.ErrorEnum.TRANSACTION_SYSTEM_ERROR;

/**
 * 统一异常处理
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    public Response<Void> handleTransactionSystemException(TransactionSystemException ex) {
        //可以在sls基于TransactionSystemException做告警或者通过Prometheus上报异常数量
        logger.error("TransactionSystemException: {}", ex.getMessage(), ex);
        Response<Void> response = new Response<>(ex.getErrCode(), ex.getMessage());
        logResponse(response);
        return response;
    }

    @ExceptionHandler(TransactionThirdPartyException.class)
    @ResponseBody
    public Response<Void> handleTransactionThirdPartyException(TransactionThirdPartyException ex) {
        //可以在sls基于TransactionSystemException做告警或者通过Prometheus上报异常数量
        logger.error("TransactionThirdPartyException: {}", ex.getMessage(), ex);
        Response<Void> response = new Response<>(ex.getErrCode(), ex.getMessage());
        logResponse(response);
        return response;
    }

    @ExceptionHandler(TransactionUserException.class)
    @ResponseBody
    public Response<Void> handleTransactionUserException(TransactionUserException ex) {
        logger.warn("TransactionUserException: {}", ex.getMessage(), ex);
        Response<Void> response = new Response<>(ex.getErrCode(), ex.getMessage());
        logResponse(response);
        return response;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class,
            IllegalArgumentException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public Response<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = getErrorMap(ex);
        logger.error("ValidationException: {}", errorMap);
        Response<Void> response = new Response<>(PARAM_ERROR.getCode(), errorMap.toString());
        logResponse(response);
        return response;
    }

    private static Map<String, String> getErrorMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception ex) {
        //可以在sls基于unCategoryException做告警或者通过Prometheus上报异常数量
        logger.error("unCategoryException: {}", ex.getMessage(), ex);
        Response<Void> response = new Response<>(TRANSACTION_SYSTEM_ERROR.getCode(), "系统开了小差,请稍后再试");
        logResponse(response);
        return response;
    }

    private void logResponse(Response<Void> response) {
        // 如果这里想打印出请求的方法，参数、耗时等，可以使用线程变量进行传递，这里就不详细写了
        logger.info("result :{}", response);
    }
}
