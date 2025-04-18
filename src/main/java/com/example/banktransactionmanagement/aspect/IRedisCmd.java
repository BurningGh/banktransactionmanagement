package com.example.banktransactionmanagement.aspect;

/**
 * IRedis
 *
 * @author DENGWENJIAN1
 * @date 2025/4/18
 */
public interface IRedisCmd {

    boolean setNx(String key, int expire);

    void expire(String key, int expire);

    void del(String key);
}