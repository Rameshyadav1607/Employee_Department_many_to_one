package com.narve.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.narve.model.Department;
import com.narve.repository.DepartmentRepository;
import com.narve.requestDTO.DepartmentRequestDTO;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	public ResponseEntity<String> create(DepartmentRequestDTO reqdto) {
		
		Department dept=new Department();
		dept.setName(reqdto.getName());
		departmentRepository.save(dept);
		return new ResponseEntity<>("deptment saved",HttpStatus.CREATED);
	}

	public List<Department> getAlldeprtments() {
		
		return departmentRepository.findAll();
	}
	

	public ResponseEntity<Void> deletedepart(Long id) {
		
		          Optional<Department>  isvailable=departmentRepository.findById(id);
		          if(isvailable.isPresent()) {
		        	  departmentRepository.deleteById(id);
		        	  return new ResponseEntity<>(HttpStatus.CONFLICT);
		          }
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> updatedepartment(Long id, DepartmentRequestDTO reqdto) {
		
		
		 Optional<Department>  isvailable=departmentRepository.findById(id);
         if(isvailable.isPresent()) {
       	         Department    department=isvailable.get();
       	         department.setName(reqdto.getName());
       	      departmentRepository.save(department);
       	  return new ResponseEntity<>("record updated",HttpStatus.OK);
         }
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	 public List<Department> getDepartmentsByName(String name) {
	        return departmentRepository.findByName(name);
	    }
}
