package com.kiranaRegister.kiranaRegister.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {

    @Id
    @GeneratedValue
    private int id;

    private double amount;
    private String currency;
    private String transactionType;
    private LocalDateTime timestamp;
}
