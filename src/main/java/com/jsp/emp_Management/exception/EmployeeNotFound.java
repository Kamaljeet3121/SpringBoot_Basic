package com.jsp.emp_Management.exception;

public class EmployeeNotFound extends RuntimeException{

    String message;

    public EmployeeNotFound(String message) {
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

}
