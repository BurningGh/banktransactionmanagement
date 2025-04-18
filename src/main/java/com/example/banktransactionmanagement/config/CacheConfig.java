package com.example.banktransactionmanagement.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.banktransactionmanagement.domain.TransactionDomainService.TRANSACTIONS_CACHE;

/**
 * 缓存配置
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    @Primary
    public CacheManager cacheManager60m() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        // 设置默认的缓存配置
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .maximumSize(3500)
                .expireAfterWrite(60, TimeUnit.MINUTES);

        cacheManager.setCaffeine(caffeine);
        List<String> cacheNames = new ArrayList<>();
        cacheNames.add(TRANSACTIONS_CACHE);
        cacheManager.setCacheNames(cacheNames);
        return cacheManager;
    }

}