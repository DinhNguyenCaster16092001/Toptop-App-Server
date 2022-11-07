package com.cp2196g03g2.server.toptop.entity;

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

@Entity
@Table(name = "tbl_friend_ship")
public class FriendShip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "request_id")
	private ApplicationUser requestUser;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "accept_id")
	private ApplicationUser acceptUser;
	
	public FriendShip() {
	}

	public FriendShip(Long id, ApplicationUser requestUser, ApplicationUser acceptUser, boolean status) {
		this.id = id;
		this.requestUser = requestUser;
		this.acceptUser = acceptUser;
	}

	public FriendShip(ApplicationUser requestUser, ApplicationUser acceptUser, boolean status) {
		this.requestUser = requestUser;
		this.acceptUser = acceptUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationUser getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(ApplicationUser requestUser) {
		this.requestUser = requestUser;
	}

	public ApplicationUser getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(ApplicationUser acceptUser) {
		this.acceptUser = acceptUser;
	}

	@Override
	public String toString() {
		return "FriendShip [id=" + id + ", requestUser=" + requestUser + ", acceptUser=" + acceptUser + "]";
	}

	
}
