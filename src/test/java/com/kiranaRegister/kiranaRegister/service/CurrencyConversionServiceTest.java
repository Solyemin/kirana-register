package com.kiranaRegister.kiranaRegister.service;

import com.kiranaRegister.kiranaRegister.model.CurrencyConversionRate;
import com.kiranaRegister.kiranaRegister.repository.CurrencyConversionRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
@RunWith(PowerMockRunner.class)
public class CurrencyConversionServiceTest {

    @Mock
    CurrencyConversionRateRepository conversionRateRepository;
    @InjectMocks
    CurrencyConversionService currencyConversionService;

    @Test
    public void convertCurrencyTest()  {
        double amount = 100.0;
        String fromCurrency = "USD";
        String toCurrency = "INR";
        double conversionRateValue = 82.99;

        CurrencyConversionRate conversionRate = new CurrencyConversionRate();
        conversionRate.setConversionRate(conversionRateValue);
        Mockito.when(conversionRateRepository.findById(toCurrency)).thenReturn(java.util.Optional.of(conversionRate));

        double convertedAmount = currencyConversionService.convertCurrency(amount,fromCurrency,toCurrency);
        assertEquals(amount * conversionRateValue, convertedAmount);

    }

    @Test
    public void testConvertCurrencyConversionRateNotAvailable() throws Exception{
        double amount = 100.0;
        String fromCurrency = "USD";
        String toCurrency = "YER";
        Mockito.when(conversionRateRepository.findById(toCurrency)).thenReturn(java.util.Optional.empty());
        try {
            currencyConversionService.convertCurrency(amount,fromCurrency,toCurrency);
        } catch(RuntimeException e){
            assertEquals("Conversion rate not available for currency: ",e.getMessage());
        }
    }

}