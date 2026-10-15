package com.jsp.emp_Management.exception;

import com.jsp.emp_Management.entity.ResponseStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleEmployeeNotFound(EmployeeNotFound ex){
        log.error("Employee not found Exception is handled",ex);
        ResponseStructure<String> response = new ResponseStructure<>(HttpStatus.NOT_FOUND.value(),
                                                "Employee Not Found Exception Occurred",
                                                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EmailNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleEmailNotFound(EmailNotFound ex){
        log.error(ex.getMessage());

        ResponseStructure<String> response = new ResponseStructure<>(HttpStatus.NOT_FOUND.value(),
                                                "Email Not Found Exception Occurred",
                                                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PasswordIncorrect.class)
    public ResponseEntity<ResponseStructure<String>> handlePasswordIncorrectException(PasswordIncorrect ex){
        log.error(ex.getMessage());

        ResponseStructure<String> response = new ResponseStructure<>(HttpStatus.UNAUTHORIZED.value(),
                                                "Password Incorrect Exception Occurred",
                                                ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public ResponseEntity<ResponseStructure<String>> handleArithmeticException(Exception ex){
        log.error(ex.getMessage());
        ResponseStructure<String> response = new ResponseStructure<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                            "Arithmetic Exception Occurred",
                                                                    ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseStructure<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        log.error(ex.getMessage());

        Map<String,String> error = new HashMap<String,String>();

        for(FieldError fieldError :ex.getFieldErrors()){
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ResponseStructure<Map<String,String>> response = new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(),
                                                                "Validation Failed for 1 or more fields",
                                                                error);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmailAlreadyRegistered.class)
    public ResponseEntity<ResponseStructure<String>> handleEmailAlreadyRegistered(EmailAlreadyRegistered ex){
        log.error(ex.getMessage());

        ResponseStructure<String> response = new ResponseStructure<>(HttpStatus.CONFLICT.value(),
                                                        "Employee Already Registered, Please Login.",
                                                        ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

}
