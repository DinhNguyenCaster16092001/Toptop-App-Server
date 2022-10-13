package com.cp2196g03g2.server.toptop.dto;

public class TicketShopDto {
	private String content;
	private String reply;
	private String userid;
	
	public TicketShopDto() {
	}

	
	public TicketShopDto(String content, String reply, String userid) {
		this.content = content;
		this.reply = reply;
		this.userid = userid;
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


	@Override
	public String toString() {
		return "TicketShopDto [content=" + content + ", reply=" + reply + ", userid=" + userid + "]";
	}
	
	
	
}
