package com.narve.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.narve.requestDTO.EmployeeRequestDTO;
import com.narve.responseDTO.EmployeeResponseDTO;
import com.narve.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
	
	    @PostMapping("/save")
	    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
	        return employeeService.saveEmployee(employeeRequestDTO);
	    }
	    
	    @GetMapping("/all")
	    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
	        return employeeService.getAllEmployees();
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
	        return employeeService.deleteEmployee(id);
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
	        return employeeService.updateEmployee(id, employeeRequestDTO);
	    }
	    
}
