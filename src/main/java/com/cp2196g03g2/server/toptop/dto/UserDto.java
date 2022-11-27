package com.cp2196g03g2.server.toptop.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto {

	private String id;
	private String email;
	private String password;
	private String fullName;
	private String avatar;
	private String history;
	private String alias;
	private Long role;
	private boolean active;
	private String createdDate;
	
	public UserDto() {
	}
	
	public UserDto(String email, String password, String fullName, String avatar, String history, String alias,
			Long role, boolean active, String createdDate) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.avatar = avatar;
		this.history = history;
		this.alias = alias;
		this.role = role;
		this.active = active;
		this.createdDate = createdDate;
	}
	
	
	


	public UserDto(String email, String fullName, String avatar, String alias,
			Long role) {
		this.email = email;
		this.fullName = fullName;
		this.avatar = avatar;
		this.alias = alias;
		this.role = role;
	}
	
	public UserDto(String id, String email, String fullName, String avatar, String history, String alias) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.avatar = avatar;
		this.history = history;
		this.alias = alias;
	}

	public UserDto(String id, String email, String fullName, String avatar, String history, String alias, Long role,
			boolean active) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.avatar = avatar;
		this.history = history;
		this.alias = alias;
		this.role = role;
		this.active = active;
	}


	public boolean isActive() {
		return active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}
	
	
	
	

	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public boolean isStatus() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
				+ ", avatar=" + avatar + ", history=" + history + ", alias=" + alias + ", role=" + role + ", active="
				+ active + ", createdDate=" + createdDate + "]";
	}

	
	

	
}	
