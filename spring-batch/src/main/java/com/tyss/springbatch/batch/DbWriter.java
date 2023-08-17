
package com.tyss.springbatch.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyss.springbatch.pojo.User;
import com.tyss.springbatch.repository.UserRepository;

@Component
public class DbWriter implements ItemWriter<User> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("User information is saved."+ users);
		userRepository.saveAll(users);
	}

	

}
