package com.cp2196g03g2.server.toptop.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.service.IEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailServiceImpl implements IEmailService{

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;

	@Override
	public void sendMail(String toMail, String subject, String body) {
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment

			Map<String, Object> model = new HashMap<>();
			model.put("Name", "John");
			model.put("location", "VietName");
			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(toMail);
			helper.setText(html, true);
			helper.setSubject(subject);
			helper.setFrom("ndhdinha19059@cusc.ctu.edu.vn");
			sender.send(message);

		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

}
