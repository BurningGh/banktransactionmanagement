package com.example.banktransactionmanagement.infrastructure.repository.proxy;

import org.springframework.stereotype.Component;

/**
 * 产品代理
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Component
public class ProductProxy {
    /**
     * 获取产品名称,此处mock供演示
     *
     * @param productId 产品id
     * @return {@link String}
     */
    public String getProductName(Long productId) {
        return "mockProductName:"+productId;
    }
}    