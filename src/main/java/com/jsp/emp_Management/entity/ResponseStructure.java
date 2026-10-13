package com.jsp.emp_Management.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"statusCode","message","timeStamp","data"})
public class ResponseStructure<T> {
    private LocalDateTime timeStamp;
    private int statusCode;
    private String message;
    private T data;

    public ResponseStructure(){
        this.timeStamp = LocalDateTime.now();
    }
}
