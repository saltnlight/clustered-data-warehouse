package com.bloomberg.clustereddatawarehouse.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FXDealsResponse {

    private String dealUniqueId;
    private String fromISOCode;
    private String toISOCode;
    private BigDecimal dealAmount;
    private Timestamp dealCreatedOn;
}
