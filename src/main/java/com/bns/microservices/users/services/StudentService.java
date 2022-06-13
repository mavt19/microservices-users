package com.bns.microservices.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bns.microservices.users.models.entity.Student;

public interface StudentService {

	public Iterable<Student> findAll();
	
	public Page<Student> findAll(Pageable pageable);
	
	public Optional<Student> finById(Long id);
	
	public Student save(Student student);
	
	public void deleteById(Long id);
	
	public List<Student> findByNameOrLastname(String term);
}
