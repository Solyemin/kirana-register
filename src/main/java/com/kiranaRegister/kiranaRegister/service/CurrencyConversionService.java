package com.kiranaRegister.kiranaRegister.service;

import com.kiranaRegister.kiranaRegister.model.CurrencyConversionRate;
import com.kiranaRegister.kiranaRegister.repository.CurrencyConversionRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    @Autowired
    private CurrencyConversionRateRepository conversionRateRepository;

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // Fetch the conversion rate from the database
        CurrencyConversionRate conversionRate = conversionRateRepository.findById(toCurrency).orElse(null);

        if (conversionRate != null) {
            // Perform the currency conversion
            double rate = conversionRate.getConversionRate();
            return amount * rate;
        } else {
            throw new RuntimeException("Conversion rate not available for currency: " + toCurrency);
        }
    }

}
