package com.jsp.emp_Management.service;

import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository repo;

    public Employee signUp(Employee employee) {
        repo.save(employee);
        System.out.println("signUp Successful..");
        return employee;
    }

    public Employee updateEmp(Employee employee) {
        System.out.println("Update Successful..");
        return employee;
    }

    public Employee deleteEmp(long id) {
        Employee e = new Employee();
        e.setId(id);
        System.out.println("Delete Successful..");
        return e;
    }

    public Employee fetchEmp(long id) {
        System.out.println("Emp Data Fetched..");
        Employee e = new Employee();
        e.setId(id);
        return e;
    }

    public List<Employee> fetchAll() {
        System.out.println("Fetched All Employees...");
        return repo.findAll();
    }

    public Employee updateEmpEmail(long id, String email) {
        Employee e = new Employee();
        e.setId(id);
        e.setEmail(email);
        System.out.println("Update Successful..");
        return e;
    }

    public Employee signIn(String email, String password) {
        Employee e = new Employee();
        e.setEmail(email);
        e.setPassword(password);
        System.out.println("Sign In Successful..");
        return e;
    }
}
