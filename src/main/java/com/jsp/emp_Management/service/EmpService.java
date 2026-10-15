package com.jsp.emp_Management.service;

import com.jsp.emp_Management.dto.EmpResDto;
import com.jsp.emp_Management.dto.EmpSignupReqDto;
import com.jsp.emp_Management.dto.EmpUpdateReqDto;
import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.exception.EmailAlreadyRegistered;
import com.jsp.emp_Management.exception.EmailNotFound;
import com.jsp.emp_Management.exception.EmployeeNotFound;
import com.jsp.emp_Management.exception.PasswordIncorrect;
import com.jsp.emp_Management.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository repo;
    private final ObjectMapper objectMapper;

    public EmpResDto signUp(EmpSignupReqDto empSignupReqDto) {
        log.info("signUp employee {}", empSignupReqDto);
        if(repo.existsByEmail(empSignupReqDto.getEmail()))
            throw new EmailAlreadyRegistered("Employee with email " + empSignupReqDto.getEmail() + " already exists");

        Employee emp = objectMapper.convertValue(empSignupReqDto,Employee.class);

        Employee dbEmp =repo.save(emp);

        return objectMapper.convertValue(dbEmp,EmpResDto.class);

        }

    public EmpResDto updateEmp(EmpUpdateReqDto empUpdateReqDto) {
        log.info("updateEmp employee {}", empUpdateReqDto);
            fetchEmp(empUpdateReqDto.getId());
            Employee e =objectMapper.convertValue(empUpdateReqDto,Employee.class);
            return objectMapper.convertValue(repo.save(e),EmpResDto.class);
    }

    public EmpResDto deleteEmp(long id) {
        log.info("deleteEmp employee {}", id);
        Employee e=fetchEmp(id);
        repo.delete(e);
        return objectMapper.convertValue(e,EmpResDto.class);
    }

    public Employee fetchEmp(long id) {
        log.info("fetchEmp employee {}", id);
        return repo.findById(id).orElseThrow(()->new EmployeeNotFound("Employee Not Found in Database"));
    }

    public List<Employee> fetchAll() {
        log.info("fetchAll employees");
        return repo.findAll();
    }

    public EmpResDto updateEmpEmail(long id, String email) {
        log.info("updateEmpEmail employee for id {}", id);
        Employee e = fetchEmp(id);
        e.setEmail(email);
        return objectMapper.convertValue(repo.save(e), EmpResDto.class);

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

    public EmpResDto signIn(String email, String password) {
        log.info("signIn employee of email {}", email);
        Employee e = repo.findByEmail(email).orElseThrow(()-> new EmailNotFound("Email Not Found"));

        if(e.getPassword().equals(password)){
            return objectMapper.convertValue(e,EmpResDto.class);
        }else
            throw new PasswordIncorrect("Password Incorrect");
    }
}
