package com.tyss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.pojo.SubjectDetails;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectDetails, Integer> {

}
