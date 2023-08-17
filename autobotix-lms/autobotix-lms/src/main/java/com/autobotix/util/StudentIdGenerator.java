package com.autobotix.util;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.autobotix.entity.Student;

public class StudentIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		Student register = (Student) object;

		String firstName = register.getFirstName().substring(0, 4).toUpperCase();
		String contactNum = register.getContactNumber().substring(0,4);
		String dateOfBirth = register.getDateOfBirth().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
		return firstName + contactNum + dateOfBirth;
	}

}
