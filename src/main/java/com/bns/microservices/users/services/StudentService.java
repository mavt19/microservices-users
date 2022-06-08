package com.bns.microservices.users.services;

import java.util.List;
import java.util.Optional;

import com.bns.microservices.users.models.entity.Student;

public interface StudentService {

	public Iterable<Student> findAll();
	
	public Optional<Student> finById(Long id);
	
	public Student save(Student student);
	
	public void deleteById(Long id);
	
	public List<Student> findByNameOrLastname(String term);
}
