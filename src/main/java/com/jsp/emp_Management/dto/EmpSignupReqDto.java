package com.jsp.emp_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmpSignupReqDto {

    @NotEmpty(message = "Name Cannot be Empty")
    private String name;

    @Min(value = 15,message = "Age less than 15 not allowed")
    @Max(value =50,message = "Age greater than 50 not allowed")
    private int age;

    @Email
    private String email;
    private String password;
    private String designation;
    private double salary;
}
