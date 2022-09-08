package com.homework.homework.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("type/{type}")
    public List<Transaction> getTransactionsByType( @PathVariable("type") String type){
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("actor/{actor}")
    public List<Transaction> getTransactionsByActor( @PathVariable("actor") String actor){
        return transactionService.getTransactionsByActor(actor);
    }

    @GetMapping("datavalue/{dataValue}")
    public List<Transaction> getTransactionsByDataValue( @PathVariable("dataValue") String dataValue){
        return transactionService.getTransactionsByDataValue(dataValue);
    }

    @GetMapping("betweendates")
    public List<Transaction> getTransactionsBetweenDates(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
        return transactionService.getTransactionsBetweenDates(start,end);
    }

    @GetMapping("testDate")
    public void  testDate(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start) {
        System.out.println("it works");
    }

    @PostMapping(path = "create")
    public void registerNewTransaction(@RequestBody Transaction transaction){
        transactionService.addNewTransaction(transaction);
    }

    @DeleteMapping(path = "delete/{transactionId}")
    public void deleteTransaction(@PathVariable("transactionId") Long transactionId){
        transactionService.deleteTransactionById(transactionId);
    }

    @PutMapping(path = "update/{transactionId}")
    public void updateTransaction(
            @PathVariable("transactionId") Long transactionId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String actor,
            @RequestParam(required = false,value = "datavalue") String dataValue){
        transactionService.updateTransaction(transactionId,type,actor, dataValue);
    }
}
