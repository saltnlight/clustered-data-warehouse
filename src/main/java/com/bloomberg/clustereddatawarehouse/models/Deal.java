package com.bloomberg.clustereddatawarehouse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Deal {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    @Column(name = "created_on")
    private Timestamp createdOn;
    @Column(name = "last_modified_on")
    private Timestamp lastModifiedOn;
}