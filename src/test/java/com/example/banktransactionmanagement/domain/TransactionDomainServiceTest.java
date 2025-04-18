package com.example.banktransactionmanagement.domain;

import com.example.banktransactionmanagement.aspect.IRedisCmd;
import com.example.banktransactionmanagement.domain.event.TransactionCreatedEvent;
import com.example.banktransactionmanagement.domain.event.TransactionDeletedEvent;
import com.example.banktransactionmanagement.domain.event.TransactionUpdatedEvent;
import com.example.banktransactionmanagement.enums.TransactionStatusEnum;
import com.example.banktransactionmanagement.exception.ErrorEnum;
import com.example.banktransactionmanagement.exception.TransactionUserException;
import com.example.banktransactionmanagement.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class TransactionDomainServiceTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @MockBean
    private ApplicationEventPublisher eventPublisher;

    @MockBean
    private IRedisCmd redisCmd;

    @InjectMocks
    private TransactionDomainService transactionDomainService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // 初始化10条数据
        for (int i = 1; i <= 10; i++) {
            TransactionDO transactionDO = TransactionDO.create(TransactionCreate.builder()
                    .description("Description " + i)
                    .amount(BigDecimal.valueOf(100.00 + i))
                    .buyerId(1001L + i)
                    .productId(2001L + i)
                    .quantity(1 + i)
                    .operator("operator" + i)
                    .memo("Memo " + i)
                    .build());
            transactionRepository.insert(transactionDO);
        }
    }

    @Test
    public void createTransaction_ShouldCreateTransaction() {
        TransactionCreate transactionCreate = TransactionCreate.builder()
                .description("New Description")
                .amount(BigDecimal.valueOf(200.00))
                .buyerId(1011L)
                .productId(2011L)
                .quantity(11)
                .operator("newOperator")
                .memo("New Memo")
                .build();

        TransactionDO createdTransaction = transactionDomainService.createTransaction(transactionCreate);

        assertNotNull(createdTransaction.getId());
        assertEquals("New Description", createdTransaction.getDescription());
        assertEquals(BigDecimal.valueOf(200.00), createdTransaction.getAmount());
        assertEquals(Long.valueOf(1011L), createdTransaction.getBuyerId());
        assertEquals(Long.valueOf(2011L), createdTransaction.getProductId());
        assertEquals(Integer.valueOf(11), createdTransaction.getQuantity());
        assertEquals("newOperator", createdTransaction.getCreateBy());
        assertEquals("New Memo", createdTransaction.getMemo());
        assertEquals(TransactionStatusEnum.PENDING.getCode(), createdTransaction.getStatus());

        verify(eventPublisher, times(1)).publishEvent(any(TransactionCreatedEvent.class));
    }

    @Test
    public void updateTransaction_ShouldUpdateTransaction() {
        TransactionDO existingTransaction = transactionRepository.selectById(1L);
        TransactionUpdate transactionUpdate = TransactionUpdate.builder()
                .id(existingTransaction.getId())
                .operator("updatedOperator")
                .memo("Updated Memo")
                .description("Updated Description")
                .amount(BigDecimal.valueOf(300.00))
                .quantity(2)
                .build();

        when(redisCmd.setNx(anyString(), anyInt())).thenReturn(true);

        TransactionDO updatedTransaction = transactionDomainService.updateTransaction(transactionUpdate);

        assertEquals("Updated Description", updatedTransaction.getDescription());
        assertEquals(BigDecimal.valueOf(300.00), updatedTransaction.getAmount());
        assertEquals(Integer.valueOf(2), updatedTransaction.getQuantity());
        assertEquals("updatedOperator", updatedTransaction.getUpdateBy());
        assertEquals("Updated Memo", updatedTransaction.getMemo());

        verify(eventPublisher, times(1)).publishEvent(any(TransactionUpdatedEvent.class));
        verify(redisCmd, times(1)).setNx(anyString(), anyInt());
        verify(redisCmd, times(1)).del(anyString());
    }

    @Test
    public void deleteTransaction_ShouldDeleteTransaction() {
        TransactionDO existingTransaction = transactionRepository.selectById(1L);
        TransactionDelete transactionDelete = TransactionDelete.builder()
                .id(existingTransaction.getId())
                .operator("deletingOperator")
                .memo("Deleting Memo")
                .build();

        when(redisCmd.setNx(anyString(), anyInt())).thenReturn(true);

        transactionDomainService.deleteTransaction(transactionDelete);

        TransactionDO deletedTransaction = transactionRepository.selectById(1L);
        assertNull(deletedTransaction);

        verify(eventPublisher, times(1)).publishEvent(any(TransactionDeletedEvent.class));
        verify(redisCmd, times(1)).setNx(anyString(), anyInt());
        verify(redisCmd, times(1)).del(anyString());
    }

    @Test
    public void updateTransaction_ShouldThrowExceptionWhenTransactionNotFound() {
        TransactionUpdate transactionUpdate = TransactionUpdate.builder()
                .id(100L) // 不存在的ID
                .operator("updatedOperator")
                .memo("Updated Memo")
                .description("Updated Description")
                .amount(BigDecimal.valueOf(300.00))
                .quantity(2)
                .build();

        when(redisCmd.setNx(anyString(), anyInt())).thenReturn(true);

        TransactionUserException exception = assertThrows(TransactionUserException.class, () -> {
            transactionDomainService.updateTransaction(transactionUpdate);
        });

        assertEquals(ErrorEnum.TRANSACTION_NOT_EXIST.getMessage(), exception.getMessage());
        verify(redisCmd, times(1)).setNx(anyString(), anyInt());
        verify(redisCmd, times(0)).del(anyString());
    }

    @Test
    public void deleteTransaction_ShouldThrowExceptionWhenTransactionNotFound() {
        TransactionDelete transactionDelete = TransactionDelete.builder()
                .id(100L) // 不存在的ID
                .operator("deletingOperator")
                .memo("Deleting Memo")
                .build();

        when(redisCmd.setNx(anyString(), anyInt())).thenReturn(true);

        TransactionUserException exception = assertThrows(TransactionUserException.class, () -> {
            transactionDomainService.deleteTransaction(transactionDelete);
        });

        assertEquals(ErrorEnum.TRANSACTION_NOT_EXIST.getMessage(), exception.getMessage());
        verify(redisCmd, times(1)).setNx(anyString(), anyInt());
        verify(redisCmd, times(0)).del(anyString());
    }
}
