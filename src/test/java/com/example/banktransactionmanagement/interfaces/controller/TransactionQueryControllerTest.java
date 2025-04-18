package com.example.banktransactionmanagement.interfaces.controller;

import com.example.banktransactionmanagement.application.service.TransactionQueryService;
import com.example.banktransactionmanagement.response.TransactionVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionQueryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionQueryService transactionQueryService;

    @InjectMocks
    private TransactionQueryController transactionQueryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionQueryController).build();
    }

    @Test
    public void listAllTransactions_SuccessfulRetrieval_ReturnsTransactions() throws Exception {
        // 准备
        TransactionVO transaction1 = new TransactionVO();
        transaction1.setId("1");
        transaction1.setAmount(new BigDecimal("100.00"));

        TransactionVO transaction2 = new TransactionVO();
        transaction2.setId("2");
        transaction2.setAmount(new BigDecimal("200.00"));

        when(transactionQueryService.listAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        // 执行和断言
        mockMvc.perform(get("/api/transactions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"code\":\"000000\",\"content\":[{\"id\":\"1\",\"amount\":100.00}, {\"id\":\"2\",\"amount\":200.00}],\"message\":\"成功\",\"success\":true}"));
    }


    @Test
    public void getById_SuccessfulRetrieval_ReturnsTransaction() throws Exception {
        // 准备
        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setId("1");
        transactionVO.setAmount(new BigDecimal("100.00"));
        transactionVO.setBuyerName("John Doe");
        transactionVO.setProductName("Product A");
        transactionVO.setQuantity(1);
        transactionVO.setStatus(1);
        transactionVO.setDescription("Test transaction");

        when(transactionQueryService.getById(1L)).thenReturn(transactionVO);

        // 执行和断言
        mockMvc.perform(get("/api/transactions/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"code\":\"000000\",\"content\":{\"id\":\"1\",\"amount\":100.00,\"buyerName\":\"John Doe\",\"productName\":\"Product A\",\"quantity\":1,\"status\":1,\"description\":\"Test transaction\"},\"message\":\"成功\",\"success\":true}"));
    }

    @Test
    public void getById_IdDoesNotExist_ReturnsEmptyTransaction() throws Exception {
        // 准备
        when(transactionQueryService.getById(2L)).thenReturn(null);

        // 执行和断言
        mockMvc.perform(get("/api/transactions/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"code\":\"000000\",\"content\":null,\"message\":\"成功\",\"success\":true}"));
    }


}
