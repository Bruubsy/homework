package com.homework.homework.transaction;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @CreationTimestamp
    private LocalDateTime timeStamp;
    private String type;
    private String actor;
    @ElementCollection
    private Map<String, String> data;

    public Transaction(String type, String actor, Map<String, String> data) {
        this.type = type;
        this.actor = actor;
        this.data = data;
    }

    public Transaction(LocalDateTime timeStamp, String type, String actor, Map<String, String> data) {
        this.timeStamp = timeStamp;
        this.type = type;
        this.actor = actor;
        this.data = data;
    }
}
