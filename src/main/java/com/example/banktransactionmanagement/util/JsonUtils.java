package com.example.banktransactionmanagement.util;

import com.example.banktransactionmanagement.exception.TransactionSystemException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtils
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class JsonUtils {
    static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    public static String toJsonStr(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new TransactionSystemException(e.getMessage(), e);
        }
    }
}