package com.jsp.emp_Management.service;

import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.exception.EmailNotFound;
import com.jsp.emp_Management.exception.EmployeeNotFound;
import com.jsp.emp_Management.exception.PasswordIncorrectException;
import com.jsp.emp_Management.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Employee> emp = repo.findById(employee.getId());
        if(emp.isPresent()){
            repo.save(employee);
            System.out.println("Update Successful..");
        }else
            throw new EmployeeNotFound("Employee Not Found");

        return employee;
    }

    public Employee deleteEmp(long id) {
        Employee e =repo.findById(id).orElseThrow(()-> new EmployeeNotFound("Employee Not Found"));
        repo.delete(e);
        return e;
    }

    public Employee fetchEmp(long id) {
        Optional<Employee> emp = repo.findById(id);
        if(emp.isPresent()){
            return emp.get();
        }else {
            throw new EmployeeNotFound("Employee Not Found");
        }

    }

    public List<Employee> fetchAll() {
        System.out.println("Fetched All Employees...");
        return repo.findAll();
    }

    public Employee updateEmpEmail(long id, String email) {

        Employee e = repo.findById(id).orElseThrow(()-> new EmployeeNotFound("Employe Doesn't Exist"));
        e.setEmail(email);
        repo.save(e);
        return e;



//        Optional<Employee> e = repo.findById(id);
//        if(e.isPresent()){
//            Employee emp = e.get();
//            emp.setEmail(email);
//            repo.save(emp);
//            System.out.println("Update Successful..");
//            return e.get();
//        }else
//            throw new EmployeeNotFound("Employee Not Found");
    }

    public Employee signIn(String email, String password) {
        Employee e = repo.findByEmailAndPassword(email,password).orElseThrow(()-> new EmailNotFound("Email Doesn't Exist"));
        if(e.getPassword().equals(password)){
            return e;
        }else
            throw new PasswordIncorrectException("Wrong Password");
    }
}
