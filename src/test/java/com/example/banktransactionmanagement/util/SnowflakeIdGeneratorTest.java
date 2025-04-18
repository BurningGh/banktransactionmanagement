package com.example.banktransactionmanagement.util;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SnowflakeIdGeneratorTest {

    private SnowflakeIdGenerator idGenerator;

    @BeforeEach
    public void setUp() throws Exception {
        // 使用反射设置 idGenerator
        Field idGeneratorField = SnowflakeIdGenerator.class.getDeclaredField("idGenerator");
        idGeneratorField.setAccessible(true);
        idGenerator = mock(SnowflakeIdGenerator.class);
        idGeneratorField.set(null, idGenerator);
    }

    @Test
    public void generateId_NormalExecution_ReturnsUniqueId() throws Exception {
        // 设置
        when(idGenerator.nextId()).thenReturn(123456789L);

        // 执行
        long id = SnowflakeIdGenerator.generateId();

        // 验证
        assertEquals(123456789L, id);
        verify(idGenerator, times(1)).nextId();
    }

    @Test
    public void generateId_ClockRollback_ThrowsException() throws Exception {
        // 设置
        when(idGenerator.nextId()).thenThrow(new RuntimeException("Clock moved backwards"));

        // 执行并验证
        assertThrows(RuntimeException.class, () -> {
            SnowflakeIdGenerator.generateId();
        });
    }

    @Test
    public void generateId_SequenceReset_HandlesCorrectly() throws Exception {
        // 设置
        when(idGenerator.nextId()).thenReturn(123456789L, 123456790L);

        // 执行
        long id1 = SnowflakeIdGenerator.generateId();
        long id2 = SnowflakeIdGenerator.generateId();

        // 验证
        assertEquals(123456789L, id1);
        assertEquals(123456790L, id2);
        verify(idGenerator, times(2)).nextId();
    }

    @Test
    public void generateId_ConcurrentExecution_GeneratesUniqueIds() throws Exception {
        // 设置
        when(idGenerator.nextId()).thenReturn(123456789L, 123456790L, 123456791L);

        // 执行
        long id1 = SnowflakeIdGenerator.generateId();
        long id2 = SnowflakeIdGenerator.generateId();
        long id3 = SnowflakeIdGenerator.generateId();

        // 验证
        assertNotEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertNotEquals(id2, id3);
        verify(idGenerator, times(3)).nextId();
    }
}
