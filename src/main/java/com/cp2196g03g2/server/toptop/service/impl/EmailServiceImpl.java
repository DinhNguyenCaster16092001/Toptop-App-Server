package com.cp2196g03g2.server.toptop.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.cp2196g03g2.server.toptop.exception.InternalServerException;

@Service
public class EmailServiceImpl {

	private final JavaMailSender emailSender;
	private final SpringTemplateEngine templateEngine;

	public EmailServiceImpl(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
		this.emailSender = emailSender;
		this.templateEngine = templateEngine;
	}

	public void sendMail(String to, String subject, String template, HashMap<String, Object> model)
			 {
		try {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(model);
		helper.setFrom("dinhcoix555@gmail.com");
		helper.setTo("dinhcoix555@gmail.com");
		helper.setSubject(subject);
		String html = templateEngine.process(template, context);
		helper.setText(html, true);
		emailSender.send(message);
		}catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

}
