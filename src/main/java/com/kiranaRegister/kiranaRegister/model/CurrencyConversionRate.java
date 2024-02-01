package com.kiranaRegister.kiranaRegister.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currency_conversion_rates")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CurrencyConversionRate {

    @Id
    @GeneratedValue
    private String currencyCode;

    private double conversionRate;
}
