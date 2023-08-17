package com.tyss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.pojo.ExamDetails;

@Repository
public interface ExamRepository extends JpaRepository<ExamDetails, Integer> {

}
