package com.example.banktransactionmanagement.infrastructure.repository;

import com.example.banktransactionmanagement.domain.AuditLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuditLogRepository extends BaseMapper<AuditLogDO> {
}    