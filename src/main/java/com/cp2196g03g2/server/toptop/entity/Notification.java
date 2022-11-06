package com.cp2196g03g2.server.toptop.entity;

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

import com.cp2196g03g2.server.toptop.enums.NotificationType;

@Entity
@Table(name = "tbl_notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "toId")
	private ApplicationUser userTo;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "fromId")
	private ApplicationUser userFrom;
	
	
	@Column
	private Boolean delivered;

	@Column
	private Boolean readed;
	
	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private NotificationType notificationType;

	
	
	public Notification() {
	}

	public Notification(Integer id, ApplicationUser userTo, ApplicationUser userFrom, Boolean delivered, Boolean readed,
			NotificationType notificationType) {
		this.id = id;
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.delivered = delivered;
		this.readed = readed;
		this.notificationType = notificationType;
	}

	public Notification(ApplicationUser userTo, ApplicationUser userFrom, Boolean delivered, Boolean readed,
			NotificationType notificationType) {
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.delivered = delivered;
		this.readed = readed;
		this.notificationType = notificationType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ApplicationUser getUserTo() {
		return userTo;
	}

	public void setUserTo(ApplicationUser userTo) {
		this.userTo = userTo;
	}

	public ApplicationUser getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(ApplicationUser userFrom) {
		this.userFrom = userFrom;
	}

	public Boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", userTo=" + userTo + ", userFrom=" + userFrom + ", delivered=" + delivered
				+ ", readed=" + readed + ", notificationType=" + notificationType + "]";
	}
	
}
