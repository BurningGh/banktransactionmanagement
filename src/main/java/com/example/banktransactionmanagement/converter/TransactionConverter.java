package com.example.banktransactionmanagement.converter;

import com.example.banktransactionmanagement.domain.TransactionCreate;
import com.example.banktransactionmanagement.domain.TransactionDO;
import com.example.banktransactionmanagement.domain.TransactionDelete;
import com.example.banktransactionmanagement.domain.TransactionUpdate;
import com.example.banktransactionmanagement.request.TransactionCreateCmd;
import com.example.banktransactionmanagement.request.TransactionDeleteCmd;
import com.example.banktransactionmanagement.request.TransactionUpdateCmd;
import com.example.banktransactionmanagement.response.TransactionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * TransactionConverter
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Mapper
public interface TransactionConverter {

    TransactionConverter INSTANCE = Mappers.getMapper(TransactionConverter.class);
    @Mapping(target = "id", source = "id", qualifiedByName = "longToString")
    TransactionVO map(TransactionDO condition);

    List<TransactionVO> maps(List<TransactionDO> condition);

    TransactionCreate map(TransactionCreateCmd condition);

    TransactionUpdate map(TransactionUpdateCmd condition);

    TransactionDelete map(TransactionDeleteCmd condition);

    // 自定义方法将 Long 转换为 String
    @Named(value = "longToString")
    default String longToString(Long id) {
        return id != null ? id.toString() : null;
    }
}