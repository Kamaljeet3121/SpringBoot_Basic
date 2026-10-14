package com.jsp.emp_Management.dto;

import lombok.Data;

@Data
public class EmpSignupReqDto {
    private String name;
    private int age;
    private String email;
    private String password;
    private String designation;
    private double salary;
}
