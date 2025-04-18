
package com.example.banktransactionmanagement.aspect;


import com.example.banktransactionmanagement.exception.TransactionSystemException;
import com.example.banktransactionmanagement.util.MD5Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IdempotentAspectTest {

    @Mock
    private IRedisCmd redisCmd;

    @InjectMocks
    private IdempotentAspect idempotentAspect;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateKey_ParamMd5Strategy_ReturnsCorrectKey() {
        String methodName = "testMethod";
        Idempotent idempotent = new Idempotent() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String expire() {
                return "600";
            }

            @Override
            public String key() {
                return "#arg0";
            }

            @Override
            public KeyStrategy keyStrategy() {
                return KeyStrategy.PARAM_MD5;
            }

            @Override
            public boolean retry() {
                return true;
            }
        };
        Object[] args = {"testArg"};

        String expectedKey = MD5Utils.md5Hex(methodName + "testArg", "UTF-8");
        String actualKey = idempotentAspect.generateKey(methodName, idempotent, args);

        assertEquals(expectedKey, actualKey);
    }

    @Test
    public void generateKey_ParamSpecificStrategy_ReturnsCorrectKey() {
        String methodName = "testMethod";
        Idempotent idempotent = new Idempotent() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String expire() {
                return "600";
            }

            @Override
            public String key() {
                return "#arg0";
            }

            @Override
            public KeyStrategy keyStrategy() {
                return KeyStrategy.PARAM_SPECIFIC;
            }

            @Override
            public boolean retry() {
                return true;
            }
        };
        Object[] args = {"testArg"};

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("arg0", args[0]);
        String expectedKey = MD5Utils.md5Hex(methodName + parser.parseExpression(idempotent.key()).getValue(context, String.class), "UTF-8");
        String actualKey = idempotentAspect.generateKey(methodName, idempotent, args);

        assertEquals(expectedKey, actualKey);
    }

    @Test
    public void generateKey_InvalidStrategy_ThrowsTransactionSystemException() {
        String methodName = "testMethod";
        Idempotent idempotent = new Idempotent() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String expire() {
                return "600";
            }

            @Override
            public String key() {
                return "#arg0";
            }

            @Override
            public KeyStrategy keyStrategy() {
                return null; // 无效的策略
            }

            @Override
            public boolean retry() {
                return true;
            }
        };
        Object[] args = {"testArg"};

        assertThrows(TransactionSystemException.class, () -> {
            idempotentAspect.generateKey(methodName, idempotent, args);
        });
    }
}