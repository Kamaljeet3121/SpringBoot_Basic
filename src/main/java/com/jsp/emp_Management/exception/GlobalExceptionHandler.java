package com.jsp.emp_Management.exception;

import com.jsp.emp_Management.entity.ResponseStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleEmployeeNotFound(EmployeeNotFound ex){
        log.error(ex.getMessage());
        ex.printStackTrace();
        ResponseStructure<String> response = new ResponseStructure();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Employee Not Found Exception Occured");
        response.setData(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(EmailNotFound.class)
    public ResponseEntity<String> handleEmailNotFound(EmailNotFound ex){
        log.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<String> handlePasswordIncorrectException(PasswordIncorrectException ex){
        log.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public ResponseEntity<ResponseStructure<String>> handleArithmeticException(Exception ex){
        log.error(ex.getMessage());
        ex.printStackTrace();
        ResponseStructure<String> response = new ResponseStructure();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Arithmetic Exception Occured");
        response.setData(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
