package com.narve.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.narve.model.Department;
import com.narve.model.Employee;
import com.narve.repository.DepartmentRepository;
import com.narve.repository.EmployeeRepository;
import com.narve.requestDTO.EmployeeRequestDTO;
import com.narve.responseDTO.EmployeeResponseDTO;


@Service
public class EmployeeService {
	    private final EmployeeRepository employeeRepository;
	    private final DepartmentRepository departmentRepository;
	    private final ModelMapper modelMapper;
       

	    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
				ModelMapper modelMapper) {
			
			this.employeeRepository = employeeRepository;
			this.departmentRepository = departmentRepository;
			this.modelMapper = modelMapper;
			
	    }

		public ResponseEntity<String> saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
	        try {
	            Employee employee = new Employee();
	            employee.setName(employeeRequestDTO.getName());
	            
	            Department department = departmentRepository.findById(employeeRequestDTO.getDepartmentId())
	                    .orElseThrow(() -> new RuntimeException("Department not found"));
	            employee.setDepartment(department);
	            
	             
	            employeeRepository.save(employee);
	            return ResponseEntity.ok("Record saved successfully");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Error saving record: " + e.getMessage());
	        }
	    }
	    
	   

		

		public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
	        try {
	            List<EmployeeResponseDTO> employees = employeeRepository.findAll().stream()
	                    .map(emp -> modelMapper.map(emp, EmployeeResponseDTO.class))
	                    .collect(Collectors.toList());
	            return ResponseEntity.ok(employees);
	        } catch (Exception e) {
	            return ResponseEntity.internalServerError().build();
	        }
	    }
	    
	    public ResponseEntity<String> deleteEmployee(Long id) {
	        try {
	            if (employeeRepository.existsById(id)) {
	                employeeRepository.deleteById(id);
	                return ResponseEntity.ok("Employee deleted successfully");
	            } else {
	                return ResponseEntity.status(404).body("Employee not found with ID: " + id);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.internalServerError().body("Error deleting employee: " + e.getMessage());
	        }
	    }
	    
	    public ResponseEntity<?> updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {
	        if (id == null) {
	            return ResponseEntity.badRequest().body("Employee ID is required");
	        }

	        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
	        if (!optionalEmployee.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
	        }

	        Employee employee = optionalEmployee.get();

	        // Update name
	        if (employeeRequestDTO.getName() != null && !employeeRequestDTO.getName().isEmpty()) {
	            employee.setName(employeeRequestDTO.getName());
	        }

	        // Update department if exists
	        Optional<Department> departmentOpt = departmentRepository.findById(employeeRequestDTO.getDepartmentId());
	        if (departmentOpt.isPresent()) {
	            employee.setDepartment(departmentOpt.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found with ID: " + employeeRequestDTO.getDepartmentId());
	        }

	        employeeRepository.save(employee);
	        return ResponseEntity.ok("Updated successfully");
	    }

	 }
