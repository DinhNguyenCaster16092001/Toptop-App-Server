package com.cp2196g03g2.server.toptop.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tbl_message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String content;
	
	@Column(nullable = false, updatable = false)
	@JsonSerialize(as = LocalDateTime.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss dd-MM-yyyy")
	private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "sender_id")
	private ApplicationUser senderUser;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "reccive_id")
	private ApplicationUser recciveUser;

	
	
	public Message() {
	}


	public Message(Long id, String content, boolean status, ApplicationUser senderUser,
			ApplicationUser recciveUser) {
		this.id = id;
		this.content = content;
		this.senderUser = senderUser;
		this.recciveUser = recciveUser;
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}




	public ApplicationUser getSenderUser() {
		return senderUser;
	}




	public void setSenderUser(ApplicationUser senderUser) {
		this.senderUser = senderUser;
	}




	public ApplicationUser getRecciveUser() {
		return recciveUser;
	}




	public void setRecciveUser(ApplicationUser recciveUser) {
		this.recciveUser = recciveUser;
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", createdDate=" + createdDate + ", senderUser="
				+ senderUser + ", recciveUser=" + recciveUser + "]";
	}




	
	
	
	
}
