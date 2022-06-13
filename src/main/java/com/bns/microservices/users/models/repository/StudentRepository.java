package com.bns.microservices.users.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bns.microservices.users.models.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{

	@Query("select s from Student s where s.name like %?1% or s.lastname like %?1%")
	public List<Student> findByNameOrLastname(String term);
}
