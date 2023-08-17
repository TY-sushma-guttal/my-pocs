package com.autobotix.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.Feeds;

@Repository
public interface FeedRepository extends JpaRepository<Feeds, Integer> {


	List<Feeds> findByPostDateAfter(LocalDate postDate);

	List<Feeds> findByIsSentFalse();

}
