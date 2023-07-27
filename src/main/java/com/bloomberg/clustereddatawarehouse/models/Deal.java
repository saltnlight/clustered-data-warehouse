package com.bloomberg.clustereddatawarehouse.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Deal {
    @Id
    private Long Id;
    @Column(name = "created_on")
    private Timestamp createdOn;
    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;
}