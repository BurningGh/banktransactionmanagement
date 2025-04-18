package com.example.banktransactionmanagement.infrastructure.repository.proxy;

import com.example.banktransactionmanagement.aspect.IRedisCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * MockRedis
 *
 * @author DENGWENJIAN1
 * @date 2025/4/18
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MockRedisCmd implements IRedisCmd {

    private final ConcurrentHashMap<String, Integer> cache = new ConcurrentHashMap<>();

    public final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    public boolean setNx(String key, int expire) {
        if (cache.containsKey(key)) {
            return false;
        } else {
            cache.putIfAbsent(key, expire);
        }
        return true;
    }

    @Override
    public void del(String key) {
        cache.remove(key);
    }

    @Override
    public void expire(String key, int expire) {

        log.info("expire key:{},expire:{}", key,expire);
        //这里暂时不实现，仅供演示
        scheduledThreadPoolExecutor.schedule(() -> {
            cache.remove(key);
        }, expire, java.util.concurrent.TimeUnit.SECONDS);
    }
}