package com.kiranaRegister.kiranaRegister.repository;

import com.kiranaRegister.kiranaRegister.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

}
