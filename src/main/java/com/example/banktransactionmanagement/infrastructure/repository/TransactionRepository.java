package com.example.banktransactionmanagement.infrastructure.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.banktransactionmanagement.domain.TransactionDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionRepository extends BaseMapper<TransactionDO> {
}    