package com.example.banktransactionmanagement.converter;

import com.example.banktransactionmanagement.domain.TransactionCreate;
import com.example.banktransactionmanagement.domain.TransactionDO;
import com.example.banktransactionmanagement.domain.TransactionDelete;
import com.example.banktransactionmanagement.domain.TransactionUpdate;
import com.example.banktransactionmanagement.request.TransactionCreateCmd;
import com.example.banktransactionmanagement.request.TransactionDeleteCmd;
import com.example.banktransactionmanagement.request.TransactionUpdateCmd;
import com.example.banktransactionmanagement.response.TransactionVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T02:03:32+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class TransactionConverterImpl implements TransactionConverter {

    @Override
    public TransactionVO map(TransactionDO condition) {
        if ( condition == null ) {
            return null;
        }

        TransactionVO transactionVO = new TransactionVO();

        transactionVO.setId( longToString( condition.getId() ) );
        transactionVO.setCreateBy( condition.getCreateBy() );
        transactionVO.setCreateAt( condition.getCreateAt() );
        transactionVO.setUpdateBy( condition.getUpdateBy() );
        transactionVO.setUpdateAt( condition.getUpdateAt() );
        transactionVO.setDescription( condition.getDescription() );
        transactionVO.setAmount( condition.getAmount() );
        transactionVO.setBuyerId( condition.getBuyerId() );
        transactionVO.setProductId( condition.getProductId() );
        transactionVO.setQuantity( condition.getQuantity() );
        transactionVO.setStatus( condition.getStatus() );
        transactionVO.setOrderTime( condition.getOrderTime() );
        transactionVO.setPaymentTime( condition.getPaymentTime() );
        transactionVO.setMemo( condition.getMemo() );

        return transactionVO;
    }

    @Override
    public List<TransactionVO> maps(List<TransactionDO> condition) {
        if ( condition == null ) {
            return null;
        }

        List<TransactionVO> list = new ArrayList<TransactionVO>( condition.size() );
        for ( TransactionDO transactionDO : condition ) {
            list.add( map( transactionDO ) );
        }

        return list;
    }

    @Override
    public TransactionCreate map(TransactionCreateCmd condition) {
        if ( condition == null ) {
            return null;
        }

        TransactionCreate.TransactionCreateBuilder transactionCreate = TransactionCreate.builder();

        transactionCreate.description( condition.getDescription() );
        transactionCreate.amount( condition.getAmount() );
        transactionCreate.buyerId( condition.getBuyerId() );
        transactionCreate.productId( condition.getProductId() );
        transactionCreate.quantity( condition.getQuantity() );
        transactionCreate.operator( condition.getOperator() );
        transactionCreate.memo( condition.getMemo() );

        return transactionCreate.build();
    }

    @Override
    public TransactionUpdate map(TransactionUpdateCmd condition) {
        if ( condition == null ) {
            return null;
        }

        TransactionUpdate.TransactionUpdateBuilder transactionUpdate = TransactionUpdate.builder();

        transactionUpdate.operator( condition.getOperator() );
        transactionUpdate.memo( condition.getMemo() );
        transactionUpdate.id( condition.getId() );
        transactionUpdate.description( condition.getDescription() );
        transactionUpdate.amount( condition.getAmount() );
        transactionUpdate.quantity( condition.getQuantity() );

        return transactionUpdate.build();
    }

    @Override
    public TransactionDelete map(TransactionDeleteCmd condition) {
        if ( condition == null ) {
            return null;
        }

        TransactionDelete.TransactionDeleteBuilder transactionDelete = TransactionDelete.builder();

        transactionDelete.id( condition.getId() );
        transactionDelete.operator( condition.getOperator() );
        transactionDelete.memo( condition.getMemo() );

        return transactionDelete.build();
    }
}
