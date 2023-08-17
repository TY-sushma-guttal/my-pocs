package com.autobotix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer> {

}
