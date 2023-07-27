package com.bloomberg.clustereddatawarehouse.exceptions.advice;

import com.bloomberg.clustereddatawarehouse.exceptions.*;
import com.bloomberg.clustereddatawarehouse.dtos.responses.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@ControllerAdvice(annotations = RestController.class)
public class DataWarehouseApiAdvice {

    @ExceptionHandler(FutureDateException.class)
    public ResponseEntity<ErrorDto> handleFutureDateException(FutureDateException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDto(ex.getMessage(), ex.getData()));
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorDto> handleInvalidAmountException(InvalidAmountException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDto(ex.getMessage(), ex.getData()));
    }

    @ExceptionHandler(InvalidISOCodeException.class)
    public ResponseEntity<ErrorDto> handleInvalidISOCodeException(InvalidISOCodeException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDto(ex.getMessage(), ex.getData()));
    }

    @ExceptionHandler(UniqueIdExistsException.class)
    public ResponseEntity<ErrorDto> handleUniqueIdExistsException(UniqueIdExistsException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDto(ex.getMessage(), ex.getData()));
    }

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<ErrorDto> handleRequiredFieldException(RequiredFieldException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDto(ex.getMessage(), ex.getData()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(ex.getMessage()));
    }
}
