package com.narve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.narve.model.Department;
import com.narve.requestDTO.DepartmentRequestDTO;
import com.narve.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@PostMapping("/save")
	public ResponseEntity<String> create(@RequestBody DepartmentRequestDTO reqdto){
		return departmentService.create(reqdto);
		
	}
	@GetMapping("/all")
	public List<Department> getAlldeprtments() {
		return departmentService.getAlldeprtments();
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletedepart(@PathVariable Long id){
		return departmentService.deletedepart(id);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatedepartment(@PathVariable Long id,@RequestBody DepartmentRequestDTO reqdto){
		return departmentService.updatedepartment(id,reqdto);
		
	}
	
	 @GetMapping("/{name}")
	    public List<Department> getDepartmentsByName(@PathVariable String name) {
	        return departmentService.getDepartmentsByName(name);
    }

}
