package com.softtechbootcamp.case2.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse<E> implements Serializable {
    private E data;
    private HttpStatus status;
    private int statusCode;
    private Date responseDate;
    private String messages;

    public GeneralResponse(E data, HttpStatus status, int statusCode) {
        this.data = data;
        this.status = status;
        this.statusCode = statusCode;
        responseDate = new Date();
    }

    public static <E> GeneralResponse<E> of(E e, HttpStatus status, int statusCode){
        return new GeneralResponse<>(e, status, statusCode);
    }

    public static <E> GeneralResponse<E> error(E e, HttpStatus status, int statusCode){
        return new GeneralResponse<>(e, status, statusCode);
    }

    public static <E> GeneralResponse<E> empty(HttpStatus status, int statusCode){
        return new GeneralResponse<>(null, status, statusCode);
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
