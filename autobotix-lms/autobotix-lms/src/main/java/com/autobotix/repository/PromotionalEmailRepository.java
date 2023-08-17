package com.autobotix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.PromotionalEmail;

@Repository
public interface PromotionalEmailRepository extends JpaRepository<PromotionalEmail, Integer> {

	List<PromotionalEmail> findByEmailNotNull();

}
