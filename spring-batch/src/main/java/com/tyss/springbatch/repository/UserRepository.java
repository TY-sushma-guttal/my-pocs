package com.tyss.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.springbatch.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
