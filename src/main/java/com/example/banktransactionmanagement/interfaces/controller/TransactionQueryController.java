package com.example.banktransactionmanagement.interfaces.controller;

import com.example.banktransactionmanagement.application.service.TransactionQueryService;
import com.example.banktransactionmanagement.response.Response;
import com.example.banktransactionmanagement.response.TransactionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 交易查询控制器
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionQueryController {

    private final TransactionQueryService transactionQueryService;

    /**
     * 获取所有交易记录，
     * 一般要避免listAll的危险操作，这里近因为任务要求使用，实际最好要limit 参数，避免一次查询过多
     *
     * @return 包含所有交易记录的响应对象
     */
    @GetMapping
    public Response<List<TransactionVO>> listAllTransactions() {
        return new Response<>(transactionQueryService.listAll());
    }


    /**
     * 根据ID获取交易记录
     *
     * @param id 交易记录的ID
     * @return 包含指定ID交易记录的响应对象
     */
    @GetMapping("/{id}")
    public Response<TransactionVO> getById(@PathVariable @NotNull(message = "id不能为空") Long id) {
        TransactionVO transactionVO = transactionQueryService.getById(id);
        return new Response<>(transactionVO);
    }
}    