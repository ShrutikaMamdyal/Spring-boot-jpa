package com.demo.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.dto.Student;
import com.demo.service.CrudService;

@RestController
@RequestMapping("/students")
public class CrudController {
	
	@Autowired
    private CrudService studentService;
	
	 @GetMapping
	    public List<Student> getAllStudents() {
	        return studentService.getAllStudents();
	    }
	 
	 @PostMapping
	 @RequestMapping("/v1/save")
	    public String saveStudent(@RequestBody Student obj) {
		 try {
			 studentService.insertStudent(obj);
		        return "Insertion Done";
		 }catch (DataIntegrityViolationException e){
			// TODO: handle exception
			 System.out.println("Exception " + e);
			 return "Roll NUmber already present";
		}
	        
	    }
	 
	  @GetMapping("/v1/updatebyname")
	    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name , @RequestBody Student student) {
	        List<Student> list = studentService.findByNameStudent(name);
	        int flag=0;
	        for(Student stud : list) {
	        	stud.setName(student.getName());
	        	stud.setAge(student.getAge());
	        	stud.setCourse(student.getCourse());
	        	studentService.save(stud);
	        	flag=1;
	        }
	        if(flag==0)
	        	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	     // Update the list of students after the update
	        List<Student> students = studentService.getAllStudents();  
	        return ResponseEntity.status(HttpStatus.OK).body(students);
	    }
	 
	  @PostMapping
		 @RequestMapping("/v1/delete")
		    public String deleteStudent(@RequestParam String name) {
		        Object obj = studentService.delete(name);
		        if(obj.toString().equals("0")) 
		        	return "No student found with name " + name;
		        return obj+" Record(s) Deleted";
		    }
	 
}
