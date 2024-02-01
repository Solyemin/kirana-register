package com.kiranaRegister.kiranaRegister.repository;

import com.kiranaRegister.kiranaRegister.model.CurrencyConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionRate, String> {
}
