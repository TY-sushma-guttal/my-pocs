package com.tyss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.pojo.UserPojoClass;

@Repository
public interface UserPojoRepository extends JpaRepository<UserPojoClass, Integer>{

	UserPojoClass findByName(String name);

}
