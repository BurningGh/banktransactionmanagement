package com.example.banktransactionmanagement.interfaces.controller;

import com.example.banktransactionmanagement.aspect.Idempotent;
import com.example.banktransactionmanagement.converter.TransactionConverter;
import com.example.banktransactionmanagement.domain.*;
import com.example.banktransactionmanagement.request.TransactionCreateCmd;
import com.example.banktransactionmanagement.request.TransactionDeleteCmd;
import com.example.banktransactionmanagement.request.TransactionUpdateCmd;
import com.example.banktransactionmanagement.response.Response;
import com.example.banktransactionmanagement.response.TransactionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.example.banktransactionmanagement.aspect.KeyStrategy.PARAM_SPECIFIC;

/**
 * 交易操作入口
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@RestController
@RequestMapping("/api/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionCommandController {

    private  final TransactionDomainService transactionDomainService;

    @PostMapping
    @Idempotent(key = "#arg0.requestId",keyStrategy = PARAM_SPECIFIC)
    public Response<TransactionVO> createTransaction(@Valid @RequestBody TransactionCreateCmd transactionCreateCmd) {
        transactionCreateCmd.validate();
        TransactionCreate transactionCreate = TransactionConverter.INSTANCE.map(transactionCreateCmd);
        TransactionDO transactionDO = transactionDomainService.createTransaction(transactionCreate);
        return new Response<>(TransactionConverter.INSTANCE.map(transactionDO));
    }

    @PutMapping("/{id}")
    @Idempotent(key = "#arg0.requestId",keyStrategy = PARAM_SPECIFIC)
    public Response<Void> updateTransaction( @Valid @RequestBody TransactionUpdateCmd transactionUpdateCmd
            ,@PathVariable @NotNull(message = "id不能为空") Long id) {
        transactionUpdateCmd.validate();
        TransactionUpdate transactionUpdate = TransactionConverter.INSTANCE.map(transactionUpdateCmd);
        transactionDomainService.updateTransaction(transactionUpdate);
        return Response.SUCCESS_RESP;
    }

    @DeleteMapping("/{id}")
    @Idempotent(key = "#arg0.requestId",keyStrategy = PARAM_SPECIFIC)
    public Response<Void> deleteTransaction(@Valid @RequestBody TransactionDeleteCmd transactionDeleteCmd
            ,@PathVariable("id") @NotNull(message = "id不能为空") Long id) {
        transactionDeleteCmd.validate();
        TransactionDelete transactionDelete = TransactionConverter.INSTANCE.map(transactionDeleteCmd);
        transactionDomainService.deleteTransaction(transactionDelete);
        return Response.SUCCESS_RESP;
    }
}    