package com.cp2196g03g2.server.toptop.entity;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.cp2196g03g2.server.toptop.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tbl_ticketshop")
public class TicketShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "reply", columnDefinition = "TEXT")
	private String reply;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private TicketStatus status;
	
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	@JsonSerialize(as = Date.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date createdDate;

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private ApplicationUser user;

	public TicketShop() {
	}


	public TicketShop(Integer id, String content, String reply, TicketStatus status, Date createdDate) {
		this.id = id;
		this.content = content;
		this.reply = reply;
		this.status = status;
		this.createdDate = createdDate;
	}
	

	public TicketShop(String content, String reply, TicketStatus status) {
		this.content = content;
		this.reply = reply;
		this.status = status;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public TicketStatus getStatus() {
		return status;
	}


	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "TicketShop [id=" + id + ", content=" + content + ", reply=" + reply + ", status=" + status
				+ ", createdDate=" + createdDate + "]";
	}

}
