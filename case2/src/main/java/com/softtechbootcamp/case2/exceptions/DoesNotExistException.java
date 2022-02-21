package com.softtechbootcamp.case2.exceptions;

import com.softtechbootcamp.case2.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DoesNotExistException extends BusinessExceptions {

    private final String  exceptionCode;
    private final String message;
    private final String detailMessage;

    public DoesNotExistException(ErrorMessage errorMessage) {
        super(errorMessage);
        this.exceptionCode = errorMessage.getExceptionCode();
        this.message = errorMessage.getMessage();
        this.detailMessage = errorMessage.getDetailMessage();
    }

    public String getExceptionCode(){
        return exceptionCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return  detailMessage;
    }
}
