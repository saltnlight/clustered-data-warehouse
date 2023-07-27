package com.bloomberg.clustereddatawarehouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "fx_deal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FXDeal extends Deal {

    @Column(name = "deal_unique_id", updatable = false)
    private String dealUniqueId;

    @Column(name = "from_iso_code")
    private String fromISOCode;

    @Column(name = "to_iso_code")
    private String toISOCode;

    @Column(name = "deal_amount")
    private BigDecimal dealAmount;

    @Column(name = "deal_created_on")
    private Timestamp dealCreatedOn;
}