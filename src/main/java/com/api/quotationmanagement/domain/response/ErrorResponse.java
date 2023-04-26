package com.api.quotationmanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error Response")
public class ErrorResponse {

    @JsonProperty("status")
    @Schema(description = "Status", example = "HTTP status message")
    private String status;

    @JsonProperty("message")
    @Schema(description = "Message", example = "Error message.")
    private String message;

    @JsonProperty("timestamp")
    @Schema(description = "Timestamp of error", example = "\"23-04-2023 05:57:15\"")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

}
