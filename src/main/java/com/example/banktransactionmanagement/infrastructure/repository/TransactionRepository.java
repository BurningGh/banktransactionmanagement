package com.example.banktransactionmanagement.infrastructure.repository;

import com.example.banktransactionmanagement.domain.TransactionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionRepository extends BaseMapper<TransactionDO> {
}    