package com.softtechbootcamp.case2.exceptions;

import com.softtechbootcamp.case2.enums.ErrorMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@RequiredArgsConstructor
@Data
public class BusinessExceptions extends  RuntimeException{
    private final ErrorMessage errorMessage;
}
