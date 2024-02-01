package com.kiranaRegister.kiranaRegister.service;

import com.kiranaRegister.kiranaRegister.model.Transaction;
import com.kiranaRegister.kiranaRegister.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalTime;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    public Transaction recordTransaction(Transaction transaction) {
        if (transaction.getCurrency().equalsIgnoreCase("USD")) {
            // Convert USD to INR using the currency conversion service
            double inrAmount = currencyConversionService.convertCurrency(
                    transaction.getAmount(), "USD", "INR"
            );
            transaction.setAmount(inrAmount);
        }

        transaction.setTimestamp(LocalDateTime.now());

        // Save the transaction in the database
        return transactionRepository.save(transaction);
    }

    public Map<LocalDate, List<Transaction>> getDailyTransactionReport(LocalDate startDate, LocalDate endDate) {

        System.out.println("startDate :  "+ startDate);
        System.out.println("endDate :  "+ endDate);
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(LocalTime.MAX);

        System.out.println("startOfDay :  "+ startOfDay);
        System.out.println("endOfDay :  "  + endOfDay);

        List<Transaction> transactions = transactionRepository.findByTimestampBetween(startOfDay, endOfDay);
        System.out.println("transactions :  "+ transactions);
        return transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getTimestamp().toLocalDate()));

    }

}