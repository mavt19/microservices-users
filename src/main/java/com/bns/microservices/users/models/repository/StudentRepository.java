package com.bns.microservices.users.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bns.microservices.users.models.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
