package com.example.banktransactionmanagement;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * 银行交易管理启动器
 *
 * @author DENGWENJIAN1
 * @date 2025/4/19
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@Slf4j
@MapperScan(value = "com.example.banktransactionmanagement.infrastructure.repository")
public class BankTransactionManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankTransactionManagementApplication.class, args);
        log.info("启动成功");
    }

    /**
     * 计划线程池执行器
     *
     * @return {@link ScheduledThreadPoolExecutor}
     */
    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {

        return new ScheduledThreadPoolExecutor(2, new ThreadPoolExecutor.CallerRunsPolicy());
    }

}    