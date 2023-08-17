package com.autobotix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	Optional<Student> findByEmail(String email);

	Optional<Student> findByContactNumber(String contactNumber);

}
