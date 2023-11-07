package com.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.dto.Student;

@Repository
public interface CrudRepo extends JpaRepository<Student, Integer>{

	List<Student> findAllByName(String name);

	Student findByName(String name);

	Object deleteAllByName(String name);

}
