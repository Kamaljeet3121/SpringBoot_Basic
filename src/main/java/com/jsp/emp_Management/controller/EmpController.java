package com.jsp.emp_Management.controller;

import com.jsp.emp_Management.dto.EmpResDto;
import com.jsp.emp_Management.dto.EmpSignupReqDto;
import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.entity.ResponseStructure;
import com.jsp.emp_Management.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/emp")
@RequiredArgsConstructor
public class EmpController {
    private final EmpService empService;

    @PostMapping("/signUp")
    public ResponseEntity<EmpResDto> signUp(@RequestBody EmpSignupReqDto empSignupReqDto) {
        log.info("signUp Employee : {}", empSignupReqDto);
        EmpResDto empResDto = empService.signUp(empSignupReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(empResDto);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee) {
        log.info("Update Employee Request for {}", employee.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(empService.updateEmp(employee));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmp(@PathVariable long id) {
        log.info("Deleteing Employee with id {}",id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(empService.deleteEmp(id));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ResponseStructure<Employee>> fetchEmp(@PathVariable long id) {
        log.info("Fetching Employee with id {}", id);

        ResponseStructure<Employee> response = new ResponseStructure<>();
        response.setMessage("Employee Found:");
        response.setStatusCode(HttpStatus.FOUND.value());
        response.setData(empService.fetchEmp(id));
//        return new  ResponseEntity<>(empService.fetchEmp(id), HttpStatus.FOUND); / Traditional approach
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/fetchAll")
    public List<Employee> fetchAllEmp() {
        log.info("Fetching all Employees");
        return empService.fetchAll();
    }

    @PatchMapping("/updateEmail/{id}")
    public Employee updateEmpEmail(@PathVariable long id,@RequestHeader String email) {
        log.info("Updating Employee with id {}", id);
        return empService.updateEmpEmail(id,email);
    }

    @GetMapping("/signIn")
    public Employee signIn(@RequestHeader String email, @RequestHeader String password) {
        log.info("Fetching Employee with email {} and password {}", email, password);
        return empService.signIn(email,password);
    }
}
