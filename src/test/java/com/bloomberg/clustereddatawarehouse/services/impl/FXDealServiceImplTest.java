package com.bloomberg.clustereddatawarehouse.services.impl;

import com.bloomberg.clustereddatawarehouse.dtos.requests.FXDealsRequest;
import com.bloomberg.clustereddatawarehouse.dtos.responses.FXDealsResponse;
import com.bloomberg.clustereddatawarehouse.exceptions.*;
import com.bloomberg.clustereddatawarehouse.models.FXDeal;
import com.bloomberg.clustereddatawarehouse.repositories.FXDealsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FXDealServiceImplTest {

    private FXDeal fxDeals;
    private FXDealsRequest fxDealsRequest;
    private FXDealsResponse fxDealsResponse;

    @Mock
    private FXDealsRepository fxDealsRepository;

    @InjectMocks
    private FXDealServiceImpl fxDealService;

    @BeforeEach
    void setUp() {
        Long mills = System.currentTimeMillis();

        fxDeals = new FXDeal();
        fxDeals.setId(1L);
        fxDeals.setDealUniqueId("JODNGN"+mills);
        fxDeals.setDealAmount(BigDecimal.valueOf(1000.00));
        fxDeals.setFromISOCode("JOD");
        fxDeals.setFromISOCode("NGN");
        fxDeals.setDealCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

        fxDealsRequest = new FXDealsRequest();
        fxDealsRequest.setDealUniqueId("JODNGN"+mills);
        fxDealsRequest.setDealAmount(BigDecimal.valueOf(1000.00));
        fxDealsRequest.setFromISOCode("JOD");
        fxDealsRequest.setToISOCode("NGN");
        fxDealsRequest.setDealCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

        fxDealsResponse = new FXDealsResponse();
        fxDealsResponse.setDealUniqueId(fxDealsRequest.getDealUniqueId());
        fxDealsResponse.setDealAmount(fxDealsRequest.getDealAmount());
        fxDealsResponse.setFromISOCode(fxDealsRequest.getFromISOCode());
        fxDealsResponse.setToISOCode(fxDealsRequest.getToISOCode());
        fxDealsResponse.setDealCreatedOn(fxDealsRequest.getDealCreatedOn());
    }

    @Test
    void successfullySaveDeals() {
        when(fxDealsRepository.existsByDealUniqueId(fxDealsRequest.getDealUniqueId())).thenReturn(false);
        when(fxDealsRepository.save(any())).thenReturn(fxDeals);
        FXDealsResponse response = fxDealService.saveDeals(fxDealsRequest);
        assertEquals(response.getDealAmount(), BigDecimal.valueOf(1000.00));
    }

    @Test
    void unsuccessfullySaveDeals() {
        when(fxDealsRepository.existsByDealUniqueId(fxDealsRequest.getDealUniqueId())).thenReturn(true);
        assertThrows(UniqueIdExistsException.class, () -> fxDealService.saveDeals(fxDealsRequest));
    }

    @Test
    void saveDealThrowsException() {
        fxDealsRequest.setToISOCode("JOD");
        assertThrows(SameISOCurrencyException.class, () -> fxDealService.saveDeals(fxDealsRequest));

        fxDealsRequest.setToISOCode("NGN");
        fxDealsRequest.setFromISOCode("aaa");
        assertThrows(InvalidISOCodeException.class, () -> fxDealService.saveDeals(fxDealsRequest));

        fxDealsRequest.setFromISOCode("JOD");
        fxDealsRequest.setDealAmount(BigDecimal.ZERO);
        assertThrows(InvalidAmountException.class, () -> fxDealService.saveDeals(fxDealsRequest));

        fxDealsRequest.setDealAmount(BigDecimal.valueOf(500));
        fxDealsRequest.setDealUniqueId(" ");
        assertThrows(BlankStringException.class, () -> fxDealService.saveDeals(fxDealsRequest));
    }
}