package com.jsp.emp_Management.repository;

import com.jsp.emp_Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee,Long> {
}
