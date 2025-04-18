package com.example.banktransactionmanagement.util;


import com.example.banktransactionmanagement.exception.TransactionSystemException;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonUtilsTest {

    @Getter
    private static class TestObject {
        private final String name;
        private final int age;

        public TestObject(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }

    @Setter
    @Getter
    private static class NonSerializableObject {
        private Object nonSerializableField = new Object() {
            private void writeObject(java.io.ObjectOutputStream stream) throws java.io.NotSerializableException {
                throw new java.io.NotSerializableException("This object cannot be serialized");
            }
        };

    }

    @BeforeEach
    public void setUp() {
        // 如果需要，可以在此处进行任何设置
    }

    @Test
    public void toJsonStr_ValidObject_ReturnsJsonString() {
        TestObject obj = new TestObject("John Doe", 30);
        String expectedJson = "{\"name\":\"John Doe\",\"age\":30}";

        String result = JsonUtils.toJsonStr(obj);

        assertEquals(expectedJson, result);
    }

    @Test
    public void toJsonStr_NonSerializableObject_ThrowsTransactionSystemException() {
        NonSerializableObject obj = new NonSerializableObject();

        assertThrows(TransactionSystemException.class, () -> {
            JsonUtils.toJsonStr(obj);
        });
    }
}
