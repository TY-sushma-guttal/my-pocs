package com.tyss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.pojo.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
