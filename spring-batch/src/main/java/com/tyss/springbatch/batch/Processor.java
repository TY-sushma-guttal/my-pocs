package com.tyss.springbatch.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tyss.springbatch.pojo.User;

@Component
public class Processor implements ItemProcessor<User, User> {
	
	private static final Map<String, String> DEPT_NAMES = new HashMap<>();
	
	public Processor() {
		DEPT_NAMES.put("001", "Technology");
		DEPT_NAMES.put("002", "Accountance");
		DEPT_NAMES.put("003", "Operational");
		DEPT_NAMES.put("004", "Processing");
		DEPT_NAMES.put("005", "Sales");
	}

	@Override
	public User process(User user) throws Exception {
		String dept = user.getDept();
		String deptName = DEPT_NAMES.get(dept);
		user.setDept(deptName);
		System.out.println(String.format("Converted from [%s] to [%s]", dept, deptName));
		return user;
	}

}
