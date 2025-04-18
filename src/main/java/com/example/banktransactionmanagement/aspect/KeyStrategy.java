package com.example.banktransactionmanagement.aspect;

/**
 * key产生策略
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public enum KeyStrategy {
    /**
     * 参数md5
     */
    PARAM_MD5,
    /**
     * 指定参数
     */
    PARAM_SPECIFIC
}
