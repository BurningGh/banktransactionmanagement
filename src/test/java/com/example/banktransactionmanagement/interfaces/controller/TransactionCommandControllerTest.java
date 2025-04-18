package com.example.banktransactionmanagement.interfaces.controller;

import com.example.banktransactionmanagement.converter.TransactionConverter;
import com.example.banktransactionmanagement.domain.*;
import com.example.banktransactionmanagement.request.TransactionCreateCmd;
import com.example.banktransactionmanagement.request.TransactionDeleteCmd;
import com.example.banktransactionmanagement.request.TransactionUpdateCmd;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionCommandControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionDomainService transactionDomainService;

    @InjectMocks
    private TransactionCommandController transactionCommandController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionCommandController).build();
    }

    @Test
    public void testCreateUpdateDeleteTransaction() throws Exception {
        // 准备创建交易记录的数据
        TransactionCreateCmd createCmd = new TransactionCreateCmd();
        createCmd.setRequestId(1L);
        createCmd.setAmount(new BigDecimal("100.00"));
        createCmd.setBuyerId(1L);
        createCmd.setProductId(1L);
        createCmd.setQuantity(1);
        createCmd.setDescription("Initial transaction");
        createCmd.setOperator("a");

        TransactionCreate transactionCreate = TransactionConverter.INSTANCE.map(createCmd);
        TransactionDO createdTransactionDO = new TransactionDO();
        createdTransactionDO.setId(1L);
        createdTransactionDO.setAmount(new BigDecimal("100.00"));
        createdTransactionDO.setBuyerId(1L);
        createdTransactionDO.setProductId(1L);
        createdTransactionDO.setQuantity(1);
        createdTransactionDO.setStatus(1);
        createdTransactionDO.setDescription("Initial transaction");

        when(transactionDomainService.createTransaction(transactionCreate)).thenReturn(createdTransactionDO);

        // 执行创建交易记录的请求并验证响应
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(generateJson(createCmd)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // 准备更新交易记录的数据
        TransactionUpdateCmd updateCmd = new TransactionUpdateCmd();
        updateCmd.setRequestId(2L);
        updateCmd.setAmount(new BigDecimal("150.00"));
        updateCmd.setQuantity(2);
        updateCmd.setDescription("Updated transaction");
        updateCmd.setOperator("a");
        updateCmd.setId(1L);

        TransactionUpdate transactionUpdate = TransactionConverter.INSTANCE.map(updateCmd);

        when(transactionDomainService.updateTransaction(transactionUpdate)).thenReturn(null);

        // 执行更新交易记录的请求并验证响应
        mockMvc.perform(put("/api/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(generateJson(updateCmd)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"code\":\"000000\",\"content\":null,\"message\":\"成功\",\"success\":true}"));

        // 准备删除交易记录的数据
        TransactionDeleteCmd deleteCmd = new TransactionDeleteCmd();
        deleteCmd.setRequestId(3L);
        deleteCmd.setOperator("a");
        deleteCmd.setId(1L);

        TransactionDelete transactionDelete = TransactionConverter.INSTANCE.map(deleteCmd);

        when(transactionDomainService.deleteTransaction(transactionDelete)).thenReturn(null);
        // 执行删除交易记录的请求并验证响应
        mockMvc.perform(delete("/api/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(generateJson(deleteCmd)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"code\":\"000000\",\"content\":null,\"message\":\"成功\",\"success\":true}"));
    }

    // 封装JSON生成逻辑
    private String generateJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
