package com.example.banktransactionmanagement.infrastructure.repository.proxy;

import org.springframework.stereotype.Component;
/**
 * 用户代理
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Component
public class UserProxy {

    /**
     * 获取用户名称,此处mock供演示
     *
     * @param id id
     * @return {@link String}
     */
    public String getUserName(Long id) {
        return "mockUserName:"+ id;
    }

}    