package com.jsp.emp_Management.service;

import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.exception.EmailNotFound;
import com.jsp.emp_Management.exception.EmployeeNotFound;
import com.jsp.emp_Management.exception.PasswordIncorrectException;
import com.jsp.emp_Management.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository repo;

    public Employee signUp(Employee employee) {
        if(repo.existsByEmail(employee.getEmail()))
            throw new EmployeeNotFound("Employee with email " + employee.getEmail() + " already exists");

        return repo.save(employee);
    }

    public Employee updateEmp(Employee employee) {
            fetchEmp(employee.getId());
            return repo.save(employee);
    }

    public Employee deleteEmp(long id) {
        Employee e=fetchEmp(id);
        repo.delete(e);
        return e;
    }

    public Employee fetchEmp(long id) {
        return repo.findById(id).orElseThrow(()->new EmployeeNotFound("Employee Not Found in Database"));
    }

    public List<Employee> fetchAll() {
        System.out.println("Fetched All Employees...");
        return repo.findAll();
    }

    public Employee updateEmpEmail(long id, String email) {
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
        Employee e = repo.findByEmail(email).orElseThrow(()-> new EmailNotFound("Email hin bhul gye??\n Kuch ni ho sakta tumhara"));
        if(e.getPassword().equals(password)){
            return e;
        }else
            throw new PasswordIncorrectException("Password bhul gye!! \n Bnane waqt dhyan kahan tha tumhara");
    }
}
