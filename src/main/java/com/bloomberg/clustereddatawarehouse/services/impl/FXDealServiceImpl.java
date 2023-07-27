package com.bloomberg.clustereddatawarehouse.services.impl;

import com.bloomberg.clustereddatawarehouse.dtos.requests.FXDealsRequest;
import com.bloomberg.clustereddatawarehouse.dtos.responses.FXDealsResponse;
import com.bloomberg.clustereddatawarehouse.exceptions.UniqueIdExistsException;
import com.bloomberg.clustereddatawarehouse.models.FXDeal;
import com.bloomberg.clustereddatawarehouse.repositories.FXDealsRepository;
import com.bloomberg.clustereddatawarehouse.services.FXDealService;
import com.bloomberg.clustereddatawarehouse.utils.Util;
import com.bloomberg.clustereddatawarehouse.utils.Validations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FXDealServiceImpl implements FXDealService {

    private final FXDealsRepository fxDealsRepository;

    @Override
    public FXDealsResponse saveDeals(FXDealsRequest fxDealsRequest) {
        Validations.isValidFXDeal(fxDealsRequest);
        if (fxDealsRepository.existsByDealUniqueId(fxDealsRequest.getDealUniqueId())) {
            throw new UniqueIdExistsException(
                    HttpStatus.BAD_REQUEST.value(),
                    String.format("A deal with the uniqueId %s already exists", fxDealsRequest.getDealUniqueId()));
        }
        FXDeal fxDeal = new FXDeal();
        FXDealsResponse fxDealsResponse = new FXDealsResponse();
        BeanUtils.copyProperties(fxDealsRequest,fxDeal);

        fxDeal = fxDealsRepository.save(fxDeal);
        BeanUtils.copyProperties(fxDeal,fxDealsResponse);
        return fxDealsResponse;
    }

    @Override
    public Page<FXDealsResponse> fetchAllFxDeals(int pageNumber, int pageSize) {
        Pageable pageable = Util.fromPaginationRequest(pageNumber, pageSize);
        return fxDealsRepository.findAllFXDealsResponse(pageable);
    }
}
