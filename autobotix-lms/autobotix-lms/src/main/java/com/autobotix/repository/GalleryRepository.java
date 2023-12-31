package com.autobotix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {

	List<Gallery> findAllByOrderByDateDesc();

}
