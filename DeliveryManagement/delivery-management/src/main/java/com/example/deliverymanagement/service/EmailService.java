package com.example.deliverymanagement.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.example.deliverymanagement.dto.MailDTO;

public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	public Integer sendMail(MailDTO mailDto) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(mailDto.getSubject());
			helper.setText(mailDto.getBody());
			helper.setTo(mailDto.getTo());
			emailSender.send(message);
			return HttpStatus.OK.value();
		} catch (Exception exception) {
			exception.printStackTrace();
			return 500;
		}
	}

}
