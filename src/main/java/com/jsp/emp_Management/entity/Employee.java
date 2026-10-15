package com.jsp.emp_Management.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonPropertyOrder({"id","name","age","email","password",})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_sequence")
    @SequenceGenerator(initialValue = 10, allocationSize = 1,name="emp_sequence")
    private long id;
    private String name;
    private int age;
    private String email;
    private String password;
    private String designation;
    private double salary;
}
