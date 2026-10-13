package com.jsp.emp_Management.repository;

import  com.jsp.emp_Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);


}
