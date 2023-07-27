package com.bloomberg.clustereddatawarehouse.controllers;

import com.bloomberg.clustereddatawarehouse.dtos.requests.FXDealsRequest;
import com.bloomberg.clustereddatawarehouse.dtos.responses.FXDealsResponse;
import com.bloomberg.clustereddatawarehouse.services.FXDealService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/deals/fx")
public class FXDealsController {

    private final FXDealService fxDealService;

    public FXDealsController(FXDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping("/create")
    public ResponseEntity<FXDealsResponse> createFXDeals(@RequestBody FXDealsRequest fxDealsRequest) {
        return ResponseEntity.ok(fxDealService.saveDeals(fxDealsRequest));
    }

    @GetMapping()
    public ResponseEntity<Page<FXDealsResponse>> getAllFXDeals(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                              @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(fxDealService.fetchAllFxDeals(pageNumber, pageSize));
    }
}
