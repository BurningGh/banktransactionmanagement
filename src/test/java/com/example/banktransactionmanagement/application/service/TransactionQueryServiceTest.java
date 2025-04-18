package com.example.banktransactionmanagement.application.service;


import com.example.banktransactionmanagement.converter.TransactionConverter;
import com.example.banktransactionmanagement.domain.TransactionDO;
import com.example.banktransactionmanagement.infrastructure.repository.TransactionRepository;
import com.example.banktransactionmanagement.infrastructure.repository.proxy.ProductProxy;
import com.example.banktransactionmanagement.infrastructure.repository.proxy.UserProxy;
import com.example.banktransactionmanagement.response.TransactionVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionQueryServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserProxy userProxy;

    @Mock
    private ProductProxy productProxy;

    @Mock
    private TransactionConverter transactionConverter;

    @InjectMocks
    private TransactionQueryService transactionQueryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById_ValidId_ReturnsTransactionVO() {
        Long id = 1L;
        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setId(id);
        transactionDO.setAmount(BigDecimal.TEN);
        transactionDO.setBuyerId(100L);
        transactionDO.setProductId(200L);

        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setId(id.toString());
        transactionVO.setAmount(BigDecimal.TEN);
        transactionVO.setBuyerName("John Doe");
        transactionVO.setProductName("Product A");

        when(transactionRepository.selectById(id)).thenReturn(transactionDO);
        when(transactionConverter.map(any(TransactionDO.class))).thenReturn(transactionVO);
        when(userProxy.getUserName(100L)).thenReturn("John Doe");
        when(productProxy.getProductName(200L)).thenReturn("Product A");

        TransactionVO result = transactionQueryService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(BigDecimal.TEN, result.getAmount());
        assertEquals("John Doe", result.getBuyerName());
        assertEquals("Product A", result.getProductName());
    }

    @Test
    public void getById_NonExistentId_ReturnsNull() {
        Long id = 1L;

        when(transactionRepository.selectById(id)).thenReturn(null);

        TransactionVO result = transactionQueryService.getById(id);

        assertEquals(null, result);
    }

    @Test
    public void listAll_NonEmptyList_ReturnsTransactionVOList() {
        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setId(1L);
        transactionDO.setAmount(BigDecimal.TEN);
        transactionDO.setBuyerId(100L);
        transactionDO.setProductId(200L);

        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setId("1");
        transactionVO.setAmount(BigDecimal.TEN);
        transactionVO.setBuyerName("John Doe");
        transactionVO.setProductName("Product A");

        when(transactionRepository.selectList(any())).thenReturn(Collections.singletonList(transactionDO));
        when(transactionConverter.maps(anyList())).thenReturn(Collections.singletonList(transactionVO));
        when(userProxy.getUserName(100L)).thenReturn("John Doe");
        when(productProxy.getProductName(200L)).thenReturn("Product A");

        List<TransactionVO> result = transactionQueryService.listAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals(BigDecimal.TEN, result.get(0).getAmount());
        assertEquals("John Doe", result.get(0).getBuyerName());
        assertEquals("Product A", result.get(0).getProductName());
    }

    @Test
    public void listAll_EmptyList_ReturnsEmptyList() {
        when(transactionRepository.selectList(any())).thenReturn(Collections.emptyList());

        List<TransactionVO> result = transactionQueryService.listAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
