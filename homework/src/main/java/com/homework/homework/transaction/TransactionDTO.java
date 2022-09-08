package com.homework.homework.transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class TransactionDTO {
    private Long id;
    private LocalDateTime timeStamp;
    private String type;
    private String actor;
    private Map<String, String> data;
}
