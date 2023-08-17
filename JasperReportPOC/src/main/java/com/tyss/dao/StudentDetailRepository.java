package com.tyss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.pojo.StudentDetails;

@Repository
public interface StudentDetailRepository extends JpaRepository<StudentDetails, Integer> {

}
