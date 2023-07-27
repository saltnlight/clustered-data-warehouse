package com.bloomberg.clustereddatawarehouse.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FXDealsRequest {

    private String dealUniqueId;
    private String fromISOCode;
    private String toISOCode;
    private BigDecimal dealAmount;
    private Timestamp dealCreatedOn;
}
