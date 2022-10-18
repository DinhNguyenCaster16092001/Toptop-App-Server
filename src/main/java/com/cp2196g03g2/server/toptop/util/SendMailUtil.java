package com.cp2196g03g2.server.toptop.util;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.cp2196g03g2.server.toptop.dto.MailRequest;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class SendMailUtil {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	
	public void sendMail(MailRequest mailRequest) {
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment

			Map<String, Object> model = mailRequest.getModel();
			Template t = config.getTemplate(mailRequest.getTemplate());
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(mailRequest.getTo());
			helper.setText(html, true);
			helper.setSubject(mailRequest.getSubject());
			helper.setFrom(mailRequest.getFrom());
			sender.send(message);

		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}
}
