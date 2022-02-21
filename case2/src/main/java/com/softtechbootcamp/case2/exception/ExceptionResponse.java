package com.softtechbootcamp.case2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Date errorDate;
    private String exceptionCode;
    private String message;
    private String detailMessage;
}
