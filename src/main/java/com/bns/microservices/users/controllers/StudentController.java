package com.bns.microservices.users.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bns.microservices.users.models.entity.Student;
import com.bns.microservices.users.services.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public ResponseEntity<?> getAllStudents() {

		return ResponseEntity.ok().body(studentService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
		Optional<Student> studentOpt = studentService.finById(id);
		if (studentOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(studentOpt.get());
	}

	@PostMapping
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		Student studentDb = studentService.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(studentDb);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
		Optional<Student> studentOpt = studentService.finById(id);
		if (studentOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		student.setId(id);
		Student studentDb = studentService.save(student);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentDb);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
		Optional<Student> studentOpt = studentService.finById(id);
		if (studentOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		studentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
