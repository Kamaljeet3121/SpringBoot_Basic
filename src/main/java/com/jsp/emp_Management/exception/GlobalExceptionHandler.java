package com.jsp.emp_Management.exception;

import com.jsp.emp_Management.entity.ResponseStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseStructure<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.error(ex.getMessage());

        Map<String,String> error = new HashMap<String,String>();

        for(FieldError fieldError :ex.getFieldErrors()){
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ex.printStackTrace();
        ResponseStructure<Map<String,String>> response = new ResponseStructure<Map<String,String>>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation Failed for 1 or more fields");
        response.setData(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
