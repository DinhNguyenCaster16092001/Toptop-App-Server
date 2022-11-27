package com.cp2196g03g2.server.toptop.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.cp2196g03g2.server.toptop.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "fromVideoId") 
	private Video fromVideo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "fromCommentId") 
	private Comment fromComment;
	
	@Column
	private Boolean delivered;

	@Column
	private Boolean readed;
	
	@Column(name = "type")
	private int notificationType;
	
	@Transient
	private String pastTime;
	
	@Transient
	private Long videoId;
	
	@Transient
	private Long commentId;

	
	
	@JsonSerialize(as = LocalDateTime.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss dd-MM-yyyy")
	private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
	
	public Notification() {
	}

	public Notification(Integer id, ApplicationUser userTo, ApplicationUser userFrom, Video fromVideo,
			Comment fromComment, Boolean delivered, Boolean readed, int notificationType) {
		this.id = id;
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.fromVideo = fromVideo;
		this.fromComment = fromComment;
		this.delivered = delivered;
		this.readed = readed;
		this.notificationType = notificationType;
	}


	public Notification(ApplicationUser userTo, ApplicationUser userFrom, Video fromVideo, Comment fromComment,
			Boolean delivered, Boolean readed, int notificationType) {
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.fromVideo = fromVideo;
		this.fromComment = fromComment;
		this.delivered = delivered;
		this.readed = readed;
		this.notificationType = notificationType;
	}
	
	
	public Notification(ApplicationUser userTo, ApplicationUser userFrom, String content, Video fromVideo,
			Comment fromComment, Boolean delivered, Boolean readed, int notificationType) {
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.content = content;
		this.fromVideo = fromVideo;
		this.fromComment = fromComment;
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

	
	public int getNotificationType() {
		return notificationType;
	}


	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}


	@JsonBackReference
	public Video getFromVideo() {
		return fromVideo;
	}

	public void setFromVideo(Video fromVideo) {
		this.fromVideo = fromVideo;
	}
	
	@JsonBackReference
	public Comment getFromComment() {
		return fromComment;
	}


	public void setFromComment(Comment fromComment) {
		this.fromComment = fromComment;
	}
	
	
	
	public Long getVideoId() {
		if(this.fromVideo == null) {
			return null;
		}
		return this.fromVideo.getId();
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}


	public Long getCommentId() {
		if(this.fromComment == null) {
			return null;
		}
		return this.fromComment.getId();
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
	
	
	public String getPastTime() {
		return pastTime;
	}

	public void setPastTime(String pastTime) {
		this.pastTime = pastTime;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", userTo=" + userTo + ", userFrom=" + userFrom + ", delivered=" + delivered
				+ ", readed=" + readed + ", notificationType=" + notificationType + "]";
	}
	
}
