package com.example.banktransactionmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易删除命令
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDelete {


    private Long id;

    private String operator;

    /**
     * 操作备注
     */
    private String memo;


}