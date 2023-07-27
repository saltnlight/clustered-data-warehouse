package com.bloomberg.clustereddatawarehouse.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FXDealsRequest {

    @NonNull
    private String dealUniqueId;
    @NonNull
    private String fromISOCode;
    @NonNull
    private String toISOCode;
    @NonNull
    private BigDecimal dealAmount;
    @NonNull
    private Timestamp dealCreatedOn;
}
