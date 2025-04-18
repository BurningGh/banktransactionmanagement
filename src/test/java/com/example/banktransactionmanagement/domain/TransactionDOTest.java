package com.example.banktransactionmanagement.domain;


import com.example.banktransactionmanagement.enums.TransactionStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionDOTest {

    private TransactionCreate transactionCreateCmd;

    @BeforeEach
    public void setUp() {
        transactionCreateCmd = new TransactionCreate();
        transactionCreateCmd.setAmount(BigDecimal.valueOf(100.00));
        transactionCreateCmd.setBuyerId(1L);
        transactionCreateCmd.setDescription("Test Transaction");
        transactionCreateCmd.setMemo("Test Memo");
        transactionCreateCmd.setOperator("Test Operator");
        transactionCreateCmd.setProductId(1L);
        transactionCreateCmd.setQuantity(1);
    }

    @Test
    public void create_TransactionCreateCmd_TransactionDOCreated() {
        TransactionDO transactionDO = TransactionDO.create(transactionCreateCmd);

        assertNotNull(transactionDO);
        assertEquals(transactionCreateCmd.getAmount(), transactionDO.getAmount());
        assertEquals(transactionCreateCmd.getBuyerId(), transactionDO.getBuyerId());
        assertEquals(transactionCreateCmd.getDescription(), transactionDO.getDescription());
        assertEquals(transactionCreateCmd.getMemo(), transactionDO.getMemo());
        assertEquals(transactionCreateCmd.getOperator(), transactionDO.getCreateBy());
        assertEquals(transactionCreateCmd.getProductId(), transactionDO.getProductId());
        assertEquals(transactionCreateCmd.getQuantity(), transactionDO.getQuantity());
        assertEquals(TransactionStatusEnum.PENDING.getCode(), transactionDO.getStatus());
        assertNotNull(transactionDO.getOrderTime());
        assertNotNull(transactionDO.getCreateAt());
    }
}
