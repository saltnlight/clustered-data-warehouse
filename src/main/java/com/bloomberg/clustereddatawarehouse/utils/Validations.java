package com.bloomberg.clustereddatawarehouse.utils;

import com.bloomberg.clustereddatawarehouse.dtos.requests.FXDealsRequest;
import com.bloomberg.clustereddatawarehouse.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Currency;

public abstract class Validations {

    public static void isValidFXDeal(FXDealsRequest fxDealsRequest) {
        String uniqueId = fxDealsRequest.getDealUniqueId();
        String fromISO = fxDealsRequest.getFromISOCode();
        String toISO = fxDealsRequest.getToISOCode();
        BigDecimal amount = fxDealsRequest.getDealAmount();
        Timestamp createdOn = fxDealsRequest.getDealCreatedOn();

        if (uniqueId == null || fromISO == null || toISO == null || amount == null || createdOn == null) {
            throw new RequiredFieldException(HttpStatus.BAD_REQUEST.value(), "All fields are required");
        }
        if (StringUtils.isBlank(uniqueId)) {
            throw new BlankStringException(HttpStatus.BAD_REQUEST.value(), "A deal's unique identifier can not be null");
        }
        if (!StringUtils.isBlank(fromISO) && !isValidCurrency(fromISO)) {
            throw new InvalidISOCodeException(HttpStatus.BAD_REQUEST.value(), "The ordering currency is not valid");
        }
        if (!StringUtils.isBlank(toISO) && !isValidCurrency(toISO)) {
            throw new InvalidISOCodeException(HttpStatus.BAD_REQUEST.value(), "The receiving currency is not valid");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new InvalidAmountException(HttpStatus.BAD_REQUEST.value(), "Deal amount can not be less than Zero");
        }
        if (fromISO.equalsIgnoreCase(toISO)) {
            throw new SameISOCurrencyException(HttpStatus.BAD_REQUEST.value(), "The ordering currency and receiving currency can not be the same");
        }
        if (createdOn.after(Timestamp.valueOf(LocalDateTime.now()))) {
            throw new FutureDateException(HttpStatus.BAD_REQUEST.value(), "A deal can not be created with a future date");
        }
    }

    public static boolean isValidCurrency(String ISOCode) {
        return Currency.getAvailableCurrencies().stream()
                .anyMatch(currency -> currency.getCurrencyCode().equalsIgnoreCase(ISOCode));
    }
}
