package com.autobotix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.CourseOnDashBoard;

@Repository
public interface CourseOnDashBoardRepository extends JpaRepository<CourseOnDashBoard, Integer> {

}
