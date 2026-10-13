package com.jsp.emp_Management.controller;

import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.entity.ResponseStructure;
import com.jsp.emp_Management.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emp")
@RequiredArgsConstructor
public class EmpController {
    private final EmpService empService;

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(empService.updateEmp(employee));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {
        Employee e = empService.signUp(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(e);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmp(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(empService.deleteEmp(id));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ResponseStructure<Employee>> fetchEmp(@PathVariable long id) {

        ResponseStructure<Employee> response = new ResponseStructure<>();
        response.setMessage("Employee Found:");
        response.setStatusCode(HttpStatus.FOUND.value());
        response.setData(empService.fetchEmp(id));
//        return new  ResponseEntity<>(empService.fetchEmp(id), HttpStatus.FOUND); / Traditional approach
        return ResponseEntity.status(HttpStatus.FOUND).body(response);

    }

    @GetMapping("/fetchAll")
    public List<Employee> fetchAllEmp() {
        return empService.fetchAll();
    }

    @PatchMapping("/updateEmail/{id}")
    public Employee updateEmpEmail(@PathVariable long id,@RequestHeader String email) {
        return empService.updateEmpEmail(id,email);
    }

    @GetMapping("/signIn")
    public Employee signIn(@RequestHeader String email, @RequestHeader String password) {
        return empService.signIn(email,password);
    }
}
