package com.api.quotationmanagement.domain.mother;

import com.api.quotationmanagement.domain.response.ErrorResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponseMother {

    public static ErrorResponse getRequestMethodNotSupportedResponse() {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Request method 'GET' not supported",
                new Date()
        );
    }
}
