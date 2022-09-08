package com.homework.homework.transaction;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction convertDtoToEntity(TransactionDTO transactionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
