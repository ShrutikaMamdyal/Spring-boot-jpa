package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.Student;
import com.demo.repo.CrudRepo;

import jakarta.transaction.Transactional;

@Service
public class CrudService {
	
	@Autowired
    private CrudRepo studentRepository;
	
	 public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }
	 
	 public Student insertStudent(Student obj) {
	        return studentRepository.save(obj);
	    }
	 
	 @Transactional
	    public List<Student> findByNameStudent(String name) {
	        return studentRepository.findAllByName(name);
	    }
	 
	 public Student save(Student std) {
	        return studentRepository.save(std);
	    }
	 
	 @Transactional
	 public Object delete(String name) {
		 System.out.println("Deleing records with name " + name);
		 return studentRepository.deleteAllByName(name); 
	 }

}
