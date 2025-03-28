package com.narve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narve.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee,Long>{

}
