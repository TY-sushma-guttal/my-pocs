package com.autobotix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {

}
