package com.jsp.emp_Management.service;

import com.jsp.emp_Management.dto.EmpResDto;
import com.jsp.emp_Management.dto.EmpSignupReqDto;
import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.exception.EmailNotFound;
import com.jsp.emp_Management.exception.EmployeeNotFound;
import com.jsp.emp_Management.exception.PasswordIncorrectException;
import com.jsp.emp_Management.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository repo;
    private final ObjectMapper objectMapper;

    public EmpResDto signUp(EmpSignupReqDto empSignupReqDto) {
        log.info("signUp employee {}", empSignupReqDto);
        if(repo.existsByEmail(empSignupReqDto.getEmail()))
            throw new EmployeeNotFound("Employee with email " + empSignupReqDto.getEmail() + " already exists");

        Employee emp = objectMapper.convertValue(empSignupReqDto,Employee.class);

        Employee dbEmp =repo.save(emp);

        return objectMapper.convertValue(dbEmp,EmpResDto.class);


        }

    public Employee updateEmp(Employee employee) {
        log.info("updateEmp employee {}", employee);
            fetchEmp(employee.getId());
            return repo.save(employee);
    }

    public Employee deleteEmp(long id) {
        log.info("deleteEmp employee {}", id);
        Employee e=fetchEmp(id);
        repo.delete(e);
        return e;
    }

    public Employee fetchEmp(long id) {
        log.info("fetchEmp employee {}", id);
        return repo.findById(id).orElseThrow(()->new EmployeeNotFound("Employee Not Found in Database"));
    }

    public List<Employee> fetchAll() {
        log.info("fetchAll employees");
        System.out.println("Fetched All Employees...");
        return repo.findAll();
    }

    public Employee updateEmpEmail(long id, String email) {
        log.info("updateEmpEmail employee for id {}", id);
        Employee e = fetchEmp(id);
        e.setEmail(email);
        return repo.save(e);

//        Optional<Employee> e = repo.findById(id);
//        if(e.isPresent()){
//            Employee emp = e.get();
//            emp.setEmail(email);
//            repo.save(emp);
//            System.out.println("Update Successful.");
//            return e.get();
//        }else
//            throw new EmployeeNotFound("Employee Not Found");
    }

    public Employee signIn(String email, String password) {
        log.info("signIn employee of email {}", email);
        Employee e = repo.findByEmail(email).orElseThrow(()-> new EmailNotFound("Email Not Found"));
        if(e.getPassword().equals(password)){
            return e;
        }else
            throw new PasswordIncorrectException("Password Incorrect");
    }
}
