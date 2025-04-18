package com.example.banktransactionmanagement.util;

/**
 * SnowflakeIdGenerator
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
public class SnowflakeIdGenerator {

    // 使用配置文件或系统属性动态加载 workerId 和 dataCenterId
    private static final long WORKER_ID = Long.parseLong(System.getProperty("worker.id", "1"));
    private static final long DATA_CENTER_ID = Long.parseLong(System.getProperty("datacenter.id", "1"));

    // 线程安全的单例模式实例化 SnowflakeIdGenerator
    private static final SnowflakeIdGenerator idGenerator;


    // 每一部分占用的位数
    // 机器ID所占的位数
    private final long workerIdBits = 5L;
    // 数据中心ID所占的位数
    private final long datacenterIdBits = 5L;

    // 用于防止时钟回拨
    private long lastTimestamp = -1L;

    // 机器ID和数据中心ID
    private final long workerId;
    private final long datacenterId;

    // 序列号
    private long sequence = 0L;


    static {
        try {
            // 初始化 SnowflakeIdGenerator，并捕获潜在的异常
            idGenerator = new SnowflakeIdGenerator(WORKER_ID, DATA_CENTER_ID);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid workerId or dataCenterId configuration", e);
        }
    }

    public static long generateId() {
        return idGenerator.nextId();
    }

    /**
     * 构造函数
     *
     * @param workerId     工作机器ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        // 每一部分的最大值
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("worker Id can't be greater than " + maxWorkerId + " or less than 0");
        }
        long maxDatacenterId = ~(-1L << datacenterIdBits);
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("datacenter Id can't be greater than " + maxDatacenterId + " or less than 0");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个ID
     *
     * @return 下一个唯一的ID
     */
    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        // 如果当前时间小于上一次生成ID的时间戳，说明系统时钟回退过
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        // 如果是同一毫秒内生成的，则序列号自增
        // 序列号所占的位数
        long sequenceBits = 12L;
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & (~(-1L << sequenceBits));
            // 序列号溢出
            if (sequence == 0) {
                // 阻塞到下一毫秒，获得新的时间戳
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 生成ID
        // 起始的时间戳 (2023-01-01 00:00:00)
        long startTimestamp = 1672531200000L;
        // 每一部分向左的位移
        long datacenterIdShift = sequenceBits + workerIdBits;
        long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        return ((currentTimestamp - startTimestamp) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << sequenceBits) |
                sequence;
    }

    /**
     * 阻塞到下一毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        for (int i = 0; i < 10; i++) {
            System.out.println(idGenerator.nextId());
        }
    }

}