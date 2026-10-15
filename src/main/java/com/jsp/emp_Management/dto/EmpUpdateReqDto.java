package com.jsp.emp_Management.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

@Data
@JsonPropertyOrder({"id","name","age","email","password",})
public class EmpUpdateReqDto {

    @NotNull(message = "Id must be provided")
    @Positive(message = "Provide a Positive Id")
    private long id;

    @NotEmpty(message = "Name Cannot be Empty")
    private String name;

    @Min(value = 15,message = "Age less than 15 not allowed")
    @Max(value =50,message = "Age greater than 50 not allowed")
    private int age;

    @Email
    private String email;

    @ToString.Exclude
    private String password;
    private String designation;
    private double salary;
}
