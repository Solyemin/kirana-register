package com.kiranaRegister.kiranaRegister.controller;

import com.kiranaRegister.kiranaRegister.model.Transaction;
import com.kiranaRegister.kiranaRegister.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Example: POST /transactions
    @PostMapping
    public Transaction recordTransaction(@RequestBody Transaction transaction) {
        // Call the service to record the transaction
        return transactionService.recordTransaction(transaction);
    }

    @GetMapping("/daily-report")
    public Map<LocalDate, List<Transaction>> getDailyTransactionReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return transactionService.getDailyTransactionReport(startDate,endDate);
    }
}
