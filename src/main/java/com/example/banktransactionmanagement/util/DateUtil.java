package com.example.banktransactionmanagement.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * DateUtil
 *
 * @author DENGWENJIAN1
 * @date 2025/4/18
 */
public class DateUtil {

    /**
     * 将 timestamp 转换为 LocalDateTime
     *
     * @param timestamp 时间戳
     * @return LocalDateTime 对象
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}