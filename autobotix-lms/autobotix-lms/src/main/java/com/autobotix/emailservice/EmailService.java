package com.autobotix.emailservice;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.autobotix.exceptions.EmailException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {

	private final JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String sender;

	public Boolean sendEmail(String to, String body, String subject) {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setText(body);
			helper.setSubject(subject);

			javaMailSender.send(message);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailException(e.getMessage());
		}
	}

	public Boolean sendWelcomeEmail(String to) {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject("Welcome to Autobotix.");
			
			Context context = new Context();
			context.setVariable("dashboardLink", "link");

			String htmlString = templateEngine.process("welcome-email", context);
			helper.setText(htmlString, true);

			javaMailSender.send(message);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailException(e.getMessage());
		}
	}

	public Boolean sendOtpEmail(String to, Integer otp) {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject("Autobotix : OTP for Authentication");

			Context context = new Context();
			context.setVariable("otp", otp);

			String htmlString = templateEngine.process("otp-email", context);
			helper.setText(htmlString, true);

			javaMailSender.send(message);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailException(e.getMessage());
		}
	}

	public Integer sendMultipleMail(String subject, String body, Object[] to) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setText(body);

			for (int i = 0; i < to.length; i++) {
				System.err.println(to[i]);
			}
			InternetAddress[] addr = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				addr[i] = new InternetAddress((String) to[i]);

			}
			helper.setTo(addr);
			javaMailSender.send(message);
			return HttpStatus.OK.value();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailException(e.getMessage());
		}

	}

}
