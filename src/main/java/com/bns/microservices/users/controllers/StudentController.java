package com.bns.microservices.users.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bns.microservices.users.models.entity.Student;
import com.bns.microservices.users.services.StudentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public ResponseEntity<?> getAllStudents() {

		return ResponseEntity.ok().body(studentService.findAll());
	}

	@GetMapping("/page")
	public ResponseEntity<?> getAllStudents(Pageable pageable) {

		return ResponseEntity.ok().body(studentService.findAll(pageable));
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
	public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult result) {
		if(result.hasErrors()) {
			return validate(result);
		}
		Student studentDb = studentService.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(studentDb);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			return validate(result);
		}
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
	
	@GetMapping("/get-students-by-name-or-lastname/{term}")
	public ResponseEntity<?> getStudentsByNameOrLastName(@PathVariable("term") String term) {
		
		List<Student> students = studentService.findByNameOrLastname(term);
		if (students == null || students.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(students);
	}
	
	private static ResponseEntity<?> validate(BindingResult result){
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(x->{
			errors.put(x.getField(), " the field : "+ x.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
}
