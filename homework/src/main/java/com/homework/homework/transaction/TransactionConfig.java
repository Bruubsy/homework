package com.homework.homework.transaction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Configuration
public class TransactionConfig {

    @Bean
    CommandLineRunner commandLineRunner(TransactionRepository repository){
        return args -> {
            Transaction transaction1 = new Transaction(
                    "type1",
                    "actor1",
                    Map.of("key1","value1")
            );

            Transaction transaction2 = new Transaction(
                    "type2",
                    "actor2",
                    Map.of("key2","value2")
            );
            Transaction transaction3 = new Transaction(
                    "type1",
                    "actor3",
                    Map.of("key3","value3")
            );
            Transaction transaction4 = new Transaction(
                    "type4",
                    "actor1",
                    Map.of("key4","value3")
            );
            repository.saveAll(List.of(transaction1,transaction2,transaction3,transaction4));
        };
    }
}
