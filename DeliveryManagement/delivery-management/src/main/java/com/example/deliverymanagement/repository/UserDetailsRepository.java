package com.example.deliverymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliverymanagement.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

	UserDetails findByEmailId(String emailId);

}
