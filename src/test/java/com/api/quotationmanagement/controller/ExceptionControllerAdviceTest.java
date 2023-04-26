package com.api.quotationmanagement.controller;

import com.api.quotationmanagement.domain.exception.EmployeeNotFoundException;
import com.api.quotationmanagement.domain.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class ExceptionControllerAdviceTest {

    @InjectMocks
    private ExceptionControllerAdvice exceptionControllerAdvice;

    @Mock
    private EmployeeNotFoundException employeeNotFoundException;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private HttpMessageNotReadableException httpMessageNotReadableException;

    @Test
    void handleStockNotFoundException() {
        ResponseEntity<ErrorResponse> responseEntity = exceptionControllerAdvice
                .handleStockNotFoundException(employeeNotFoundException);

        then(HttpStatus.NOT_FOUND).isEqualTo(responseEntity.getStatusCode());
        then(responseEntity.getBody()).isNotNull();
        then(HttpStatus.NOT_FOUND.getReasonPhrase()).isEqualTo(responseEntity.getBody().getStatus());
        then(employeeNotFoundException.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
        then(responseEntity.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void handleMethodArgumentNotValidException() {
        ResponseEntity<ErrorResponse> responseEntity = exceptionControllerAdvice
                .handleMethodArgumentNotValidException(methodArgumentNotValidException);

        then(HttpStatus.BAD_REQUEST).isEqualTo(responseEntity.getStatusCode());
        then(responseEntity.getBody()).isNotNull();
        then(HttpStatus.BAD_REQUEST.getReasonPhrase()).isEqualTo(responseEntity.getBody().getStatus());
        then(methodArgumentNotValidException.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
        then(responseEntity.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void handleHttpMessageNotReadableException() {
        ResponseEntity<ErrorResponse> responseEntity = exceptionControllerAdvice
                .handleMethodArgumentNotValidException(httpMessageNotReadableException);

        then(HttpStatus.BAD_REQUEST).isEqualTo(responseEntity.getStatusCode());
        then(responseEntity.getBody()).isNotNull();
        then(HttpStatus.BAD_REQUEST.getReasonPhrase()).isEqualTo(responseEntity.getBody().getStatus());
        then(httpMessageNotReadableException.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
        then(responseEntity.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void handleException() {
        Exception exception = new Exception("test");
        ResponseEntity<ErrorResponse> responseEntity = exceptionControllerAdvice
                .handleException(exception);

        then(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(responseEntity.getStatusCode());
        then(responseEntity.getBody()).isNotNull();
        then(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).isEqualTo(responseEntity.getBody().getStatus());
        then(exception.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
        then(responseEntity.getBody().getTimestamp()).isNotNull();
    }
}