package com.andersonzero0.userauthservice.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Data
public class RestErrorMessage {
    private HttpStatus status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
}
