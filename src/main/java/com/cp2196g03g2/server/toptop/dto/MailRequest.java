package com.cp2196g03g2.server.toptop.dto;

import java.util.HashMap;

public class MailRequest {


	private String to;
	private String from;
	private String subject;
	private String template;
	private HashMap<String, Object> model;
	
	

	public MailRequest(String to, String from, String subject, String template, HashMap<String, Object> model) {
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.template = template;
		this.model = model;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public HashMap<String, Object> getModel() {
		return model;
	}
	public void setModel(HashMap<String, Object> model) {
		this.model = model;
	}
	
	
	
	
}
