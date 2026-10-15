package com.jsp.emp_Management.controller;

import com.jsp.emp_Management.dto.EmpResDto;
import com.jsp.emp_Management.dto.EmpSignupReqDto;
import com.jsp.emp_Management.dto.EmpUpdateReqDto;
import com.jsp.emp_Management.entity.Employee;
import com.jsp.emp_Management.entity.ResponseStructure;
import com.jsp.emp_Management.service.EmpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/emp")
@RequiredArgsConstructor
public class EmpController {
    private final EmpService empService;
    private final ObjectMapper objectMapper;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseStructure<EmpResDto>> signUp(@RequestBody @Valid EmpSignupReqDto empSignupReqDto) {
        log.info("signUp Employee : {}", empSignupReqDto);

        EmpResDto empResDto = empService.signUp(empSignupReqDto);
        ResponseStructure<EmpResDto> resStructure = new ResponseStructure<>(HttpStatus.CREATED.value(),
                                                        "Employee Registered Successfully",
                                                        empResDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resStructure);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStructure<EmpResDto>> updateEmp(@RequestBody @Valid EmpUpdateReqDto empUpdateReqDto) {

        log.info("Update Employee Request for {}", empUpdateReqDto.getId());

        ResponseStructure<EmpResDto> resStructure = new ResponseStructure<>(HttpStatus.ACCEPTED.value(),
                                                        "Employee Updated Successfully",
                                                        empService.updateEmp(empUpdateReqDto));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resStructure);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<EmpResDto>> deleteEmp(@PathVariable long id) {

        log.info("Deleteing Employee with id {}",id);

        ResponseStructure<EmpResDto> resStructure = new ResponseStructure<>(HttpStatus.ACCEPTED.value(),
                                                        "Employee Deleted Successfully",
                                                        empService.deleteEmp(id));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resStructure);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ResponseStructure<EmpResDto>> fetchEmp(@PathVariable long id) {
        log.info("Fetching Employee with id {}", id);

        ResponseStructure<EmpResDto> resStructure = new ResponseStructure<>(HttpStatus.FOUND.value(),
                                                        "Employee Found:",objectMapper.
                                                         convertValue(empService.fetchEmp(id),EmpResDto.class));

        return ResponseEntity.status(HttpStatus.FOUND).body(resStructure);
//        return new  ResponseEntity<>(empService.fetchEmp(id), HttpStatus.FOUND); // Traditional approach

    }

    @GetMapping("/fetchAll")
    public ResponseEntity<ResponseStructure<List<Employee>>> fetchAllEmp() {
        log.info("Fetching all Employees");

        ResponseStructure<List<Employee>> resStructure = new ResponseStructure<>(HttpStatus.OK.value(),
                                                        "All Employees Fetched Successfully",
                                                        empService.fetchAll());

        return ResponseEntity.status(HttpStatus.OK).body(resStructure);
    }

    @PatchMapping("/updateEmail/{id}")
    public ResponseEntity<ResponseStructure<EmpResDto>> updateEmpEmail(@PathVariable long id,@RequestHeader String email) {
        log.info("Updating Employee with id {}", id);

        ResponseStructure<EmpResDto> resStructure = new ResponseStructure<>(HttpStatus.ACCEPTED.value(),
                                                        "Employee Email Updated Successfully",
                                                        empService.updateEmpEmail(id,email));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resStructure);
    }

    @PostMapping("/signIn")
    public ResponseEntity<ResponseStructure<EmpResDto>> signIn(@RequestHeader String email, @RequestHeader String password) {
        log.info("Fetching Employee with email {} and password {}", email, password);

        ResponseStructure<EmpResDto> resStructure = new ResponseStructure<>(HttpStatus.FOUND.value(),
                                                        "Employee Signed In Successfully",
                                                        empService.signIn(email,password));

        return ResponseEntity.status(HttpStatus.FOUND).body(resStructure);
    }
}
