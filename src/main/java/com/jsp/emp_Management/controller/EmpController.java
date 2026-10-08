package com.jsp.emp_Management.controller;

import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emp")
@RequiredArgsConstructor
public class EmpController {
    private final EmpService empService;

    @PutMapping("/update")
    public Employee updateEmp(@RequestBody Employee employee) {
        return empService.updateEmp(employee);
    }

    @PostMapping("/signUp")
    public Employee signUp(@RequestBody Employee employee) {
        return empService.signUp(employee);
    }

    @DeleteMapping("/delete/{id}")
    public Employee deleteEmp(@PathVariable long id) {
        return empService.deleteEmp(id);

    }

    @GetMapping("/fetch/{id}")
    public Employee fetchEmp(@PathVariable long id) {
        return empService.fetchEmp(id);

    }

    @GetMapping("/fetchAll")
    public List<Employee> fetchAllEmp() {
        return empService.fetchAll();
    }

    @PatchMapping("/updateEmail/{id}")
    public Employee updateEmpEmail(@PathVariable long id,@RequestHeader String email) {
        return empService.updateEmpEmail(id,email);
    }

    @PostMapping("/signIn")
    public Employee signIn(@RequestHeader String email, @RequestHeader String password) {
        return empService.signIn(email,password);
    }
}
