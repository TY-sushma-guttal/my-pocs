package com.tyss.configure;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tyss.dao.UserPojoRepository;
import com.tyss.pojo.UserPojoClass;

@Service
public class UserDetailServiceImple implements UserDetailsService {
	
	@Autowired
	private UserPojoRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPojoClass userPojoClass = userRepo.findByName(username);
		return new User(userPojoClass.getName(), userPojoClass.getPassword(), new ArrayList<>());
	}

}
