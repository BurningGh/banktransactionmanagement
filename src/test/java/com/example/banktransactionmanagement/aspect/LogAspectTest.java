package com.example.banktransactionmanagement.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LogAspectTest {

    @InjectMocks
    private LogAspect logAspect;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private CodeSignature codeSignature;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getArgStr_NoArguments_ReturnsNoArguments() {
        when(joinPoint.getArgs()).thenReturn(null);

        StringBuilder result = LogAspect.getArgStr(joinPoint);

        assertEquals("No arguments", result.toString());
    }

    @Test
    public void getArgStr_SingleArgument_ReturnsArgument() {
        Object[] args = {"singleArg"};
        when(joinPoint.getArgs()).thenReturn(args);

        StringBuilder result = LogAspect.getArgStr(joinPoint);

        assertEquals("singleArg", result.toString());
    }

    @Test
    public void getArgStr_MultipleArguments_ReturnsFormattedString() {
        Object[] args = {"arg1", "arg2"};
        String[] paramNames = {"param1", "param2"};
        when(joinPoint.getArgs()).thenReturn(args);
        when(joinPoint.getSignature()).thenReturn(codeSignature);
        when(codeSignature.getParameterNames()).thenReturn(paramNames);

        StringBuilder result = LogAspect.getArgStr(joinPoint);

        assertEquals("param1=arg1,param2=arg2,", result.toString());
    }
}
