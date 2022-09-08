package com.homework.homework.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public void addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransactionById(Long transactionId) {
        boolean transactionExists = transactionRepository.existsById(transactionId);
        if (!transactionExists){
            throw new IllegalStateException("transaction with id "+transactionId+" does not exist");
        }
        transactionRepository.deleteById(transactionId);
    }

    @Transactional
    public void updateTransaction(Long transactionId, String type, String actor, String dataValue) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException("transaction with id "+transactionId+" does not exist"));

        if (type != null && type.length() >0 && !Objects.equals(transaction.getType(),type)){
            transaction.setType(type);
        }

        if (actor != null && actor.length() >0 && !Objects.equals(transaction.getActor(),actor)){
            transaction.setType(actor);
        }

        if (dataValue != null && dataValue.length() >0 && !transaction.getData().containsValue(dataValue)){
            for(String key: transaction.getData().keySet()){
                transaction.setData(Map.of(key,dataValue));
            }
        }


    }

    public List<Transaction> getTransactionsByActor(String actor) {
        return transactionRepository.findAll().stream().filter(transaction -> Objects.equals(transaction.getActor(), actor)).toList();
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findAll().stream().filter(transaction -> Objects.equals(transaction.getType(), type)).toList();
    }

    public List<Transaction> getTransactionsByDataValue(String dataValue) {
        return transactionRepository.findAll().stream().filter(transaction -> transaction.getData().containsValue(dataValue)).toList();
    }

    public List<Transaction> getTransactionsBetweenDates(Date start, Date end) {
        return transactionRepository.findAll().stream().filter(transaction ->
                transaction.getTimeStamp().isBefore(LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()))
                        && transaction.getTimeStamp().isAfter(LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault())))
                .toList();
    }
}
