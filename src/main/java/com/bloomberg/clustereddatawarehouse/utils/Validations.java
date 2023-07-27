package com.bloomberg.clustereddatawarehouse.utils;

import com.bloomberg.clustereddatawarehouse.dtos.requests.FXDealsRequest;
import com.bloomberg.clustereddatawarehouse.exceptions.BlankStringException;
import com.bloomberg.clustereddatawarehouse.exceptions.FutureDateException;
import com.bloomberg.clustereddatawarehouse.exceptions.InvalidAmountException;
import com.bloomberg.clustereddatawarehouse.exceptions.InvalidISOCodeException;
import com.bloomberg.clustereddatawarehouse.exceptions.SameISOCurrencyException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Currency;

public abstract class Validations {

    public static void isValidFXDeal(FXDealsRequest fxDealsRequest) {
        String uniqueId = fxDealsRequest.getDealUniqueId().trim();
        String fromISO = fxDealsRequest.getFromISOCode().trim();
        String toISO = fxDealsRequest.getToISOCode().trim();
        BigDecimal amount = fxDealsRequest.getDealAmount();
        Timestamp createdOn = fxDealsRequest.getDealCreatedOn();

        if (StringUtils.isBlank(uniqueId)) {
            throw new BlankStringException("A deal's unique identifier can not be null");
        }
        if (!StringUtils.isBlank(fromISO) && !isValidCurrency(fromISO)) {
            throw new InvalidISOCodeException("The ordering currency is not valid");
        }
        if (!StringUtils.isBlank(toISO) && !isValidCurrency(toISO)) {
            throw new InvalidISOCodeException("The receiving currency is not valid");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new InvalidAmountException("Deal amount can not be less than Zero");
        }
        if (fromISO.equalsIgnoreCase(toISO)) {
            throw new SameISOCurrencyException("The ordering currency and receiving currency can not be the same");
        }
        if (createdOn.after(Timestamp.valueOf(LocalDateTime.now()))) {
            throw new FutureDateException("A deal can not be created with a future date");
        }
    }

    public static boolean isValidCurrency(String ISOCode) {
        return Currency.getAvailableCurrencies().stream()
                .anyMatch(currency -> currency.getCurrencyCode().equalsIgnoreCase(ISOCode));
    }
}
