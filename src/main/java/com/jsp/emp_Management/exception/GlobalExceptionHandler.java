package com.jsp.emp_Management.exception;

import com.jsp.emp_Management.entity.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleEmployeeNotFound(EmployeeNotFound ex){
        ex.printStackTrace();

        ResponseStructure response = new ResponseStructure();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Employee Not Found Exception Occured");
        response.setData(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(EmailNotFound.class)
    public ResponseEntity<String> handleEmailNotFound(EmailNotFound ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<String> handlePasswordIncorrectException(PasswordIncorrectException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
