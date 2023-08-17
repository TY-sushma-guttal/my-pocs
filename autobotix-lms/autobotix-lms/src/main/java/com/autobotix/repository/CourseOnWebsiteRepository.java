package com.autobotix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.CourseOnWebsite;

@Repository
public interface CourseOnWebsiteRepository extends JpaRepository<CourseOnWebsite, Integer> {

}
