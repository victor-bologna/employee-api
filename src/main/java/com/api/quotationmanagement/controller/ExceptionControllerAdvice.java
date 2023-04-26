package com.api.quotationmanagement.controller;

import com.api.quotationmanagement.domain.exception.EmployeeNotFoundException;
import com.api.quotationmanagement.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStockNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(
                status.getReasonPhrase(),
                employeeNotFoundException.getMessage(),
                new Date()), status);
    }

    @ExceptionHandler( {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            Exception exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(
                status.getReasonPhrase(),
                exception.getMessage(),
                new Date()), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorResponse(
                status.getReasonPhrase(),
                exception.getMessage(),
                new Date()), status);
    }
}
