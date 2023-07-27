package com.bloomberg.clustereddatawarehouse.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements Serializable {
    private String status;
    private String message;
    private Object data;
    private Timestamp encounteredTime = Timestamp.valueOf(LocalDateTime.now());

    public ErrorDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ErrorDto(String message) {
        this.message = message;
    }
}
