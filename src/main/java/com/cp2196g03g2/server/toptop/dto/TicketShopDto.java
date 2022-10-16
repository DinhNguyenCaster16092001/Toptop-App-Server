package com.cp2196g03g2.server.toptop.dto;

public class TicketShopDto {
	private Integer id;
	private String content;
	private String reply;
	private String userid;
	private Integer status;
	
	public TicketShopDto() {
	}

	
	public TicketShopDto(Integer id, String reply, String userid, Integer status) {
		this.id = id;
		this.reply = reply;
		this.userid = userid;
		this.status = status;
	}
	

	public TicketShopDto(String userid, Integer status) {
		this.userid = userid;
		this.status = status;
	}


	public TicketShopDto(String content, String userid) {
		this.content = content;
		this.userid = userid;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TicketShopDto [content=" + content + ", reply=" + reply + ", userid=" + userid + "]";
	}
	
	
	
}
