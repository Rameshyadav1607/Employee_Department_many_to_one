package com.narve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narve.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	List<Department> findByName(String name);

}
