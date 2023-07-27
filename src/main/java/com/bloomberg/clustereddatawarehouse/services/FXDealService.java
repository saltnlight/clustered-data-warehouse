package com.bloomberg.clustereddatawarehouse.services;

import com.bloomberg.clustereddatawarehouse.dtos.requests.FXDealsRequest;
import com.bloomberg.clustereddatawarehouse.dtos.responses.FXDealsResponse;
import org.springframework.data.domain.Page;

public interface FXDealService {

    FXDealsResponse saveDeals(FXDealsRequest fxDealsRequest);
    Page<FXDealsResponse> fetchAllFxDeals(int pageNumber, int pageSize);
}
