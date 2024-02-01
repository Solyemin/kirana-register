package com.kiranaRegister.kiranaRegister.service;

import com.kiranaRegister.kiranaRegister.model.Transaction;
import com.kiranaRegister.kiranaRegister.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(PowerMockRunner.class)
public class TransactionServiceTest {

    @Mock
    private  CurrencyConversionService currencyConversionService;
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    TransactionService transactionService;

    @Test
    public void recordTransactionTest(){
        Transaction transaction = new Transaction();
        transaction.setCurrency("USD");
        transaction.setAmount(11.0);
        Mockito.when(currencyConversionService.convertCurrency(Mockito.anyDouble(),Mockito.anyString(),Mockito.anyString())).thenReturn(912.89);
        Mockito.doNothing().when(transactionRepository.save(Mockito.any()));
        Transaction transaction1 = transactionService.recordTransaction(transaction);
        Assert.assertNotNull(transaction1);
        assertTrue(transaction1.getCurrency().equalsIgnoreCase(transaction.getCurrency()));
    }

    @Test
    public void getDailyTransactionReportTest(){
        LocalDate startDate = LocalDate.of(2024, 2, 1);
        LocalDate endDate = LocalDate.of(2024, 2, 2);

        Transaction transaction1 = new Transaction();
        transaction1.setTimestamp(LocalDateTime.of(2024, 2, 1, 12, 0));
        Transaction transaction2 = new Transaction();
        transaction2.setTimestamp(LocalDateTime.of(2024, 2, 1, 15, 30));

        List<Transaction> mockTransactions = Arrays.asList(transaction1, transaction2);

        Mockito.when(transactionRepository.findByTimestampBetween(Mockito.any(),Mockito.any())).thenReturn(mockTransactions);

        Map<LocalDate, List<Transaction>> result = transactionService.getDailyTransactionReport(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(LocalDate.of(2024, 2, 1)));
        assertEquals(2, result.get(LocalDate.of(2024, 2, 1)).size());
    }

}