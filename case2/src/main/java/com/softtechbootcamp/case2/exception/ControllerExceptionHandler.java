package com.softtechbootcamp.case2.exception;

import com.softtechbootcamp.case2.exceptions.DoesNotExistException;
import com.softtechbootcamp.case2.exceptions.DuplicateEntityException;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.response.GeneralResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex, WebRequest webRequest) {

        Date errorDate = new Date();
        String exceptionCode = ex.getExceptionCode();
        String message = ex.getMessage();
        String detailMessage = ex.getDetailMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,exceptionCode,message, detailMessage);
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        generalResponse.setMessages(webRequest.getDescription(true));

        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public final ResponseEntity<Object> duplicateEntityException(DuplicateEntityException ex, WebRequest webRequest) {

        Date errorDate = new Date();
        String exceptionCode = ex.getExceptionCode();
        String message = ex.getMessage();
        String detailMessage = ex.getDetailMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,exceptionCode,message, detailMessage);
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse, HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
        generalResponse.setMessages(webRequest.getDescription(true));

        return new ResponseEntity<>(generalResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DoesNotExistException.class)
    public final ResponseEntity<Object> doesNotExistException(DoesNotExistException ex, WebRequest webRequest) {

        Date errorDate = new Date();
        String exceptionCode = ex.getExceptionCode();
        String message = ex.getMessage();
        String detailMessage = ex.getDetailMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,exceptionCode,message, detailMessage);
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse, HttpStatus.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE.value());
        generalResponse.setMessages(webRequest.getDescription(true));

        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String message = "Validation failed!";
        String detailMessage = ex.getBindingResult().toString();
        String exceptionCode = String.valueOf(status.value());

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate,exceptionCode,message, detailMessage);
        GeneralResponse<ExceptionResponse> generalResponse = GeneralResponse.error(exceptionResponse, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        generalResponse.setMessages(request.getDescription(true));


        return new ResponseEntity<>(generalResponse, HttpStatus.BAD_REQUEST);
    }
}