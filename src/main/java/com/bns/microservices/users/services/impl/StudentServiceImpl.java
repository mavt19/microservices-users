package com.bns.microservices.users.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bns.microservices.users.models.entity.Student;
import com.bns.microservices.users.models.repository.StudentRepository;
import com.bns.microservices.users.services.StudentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Student> finById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id);
	}

	@Override
	@Transactional
	public Student save(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> findByNameOrLastname(String term) {
		// TODO Auto-generated method stub
		return studentRepository.findByNameOrLastname(term);
	}

	@Override
	public Page<Student> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return studentRepository.findAll(pageable);
	}

}
