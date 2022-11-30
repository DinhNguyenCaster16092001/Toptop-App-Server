package com.cp2196g03g2.server.toptop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_report_video")
public class ReportVideo {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private boolean status;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private ApplicationUser user;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "typeId")
	private ReportType reportType;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "replyToId")
	private ApplicationUser replyUser;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "videoId")
	private Video video;
	
	@Transient
	private Long videoId;

	public ReportVideo() {
	}

	
	public ReportVideo(Long id, boolean status, ApplicationUser user, ReportType reportType, ApplicationUser replyUser,
			Video video) {
		this.id = id;
		this.status = status;
		this.user = user;
		this.reportType = reportType;
		this.replyUser = replyUser;
		this.video = video;
	}

	
	public ReportVideo(boolean status, ApplicationUser user, ReportType reportType, ApplicationUser replyUser,
			Video video) {
		this.status = status;
		this.user = user;
		this.reportType = reportType;
		this.replyUser = replyUser;
		this.video = video;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ApplicationUser getUser() {
		return user;
	}

	
	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}


	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	@JsonBackReference
	public ApplicationUser getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(ApplicationUser replyUser) {
		this.replyUser = replyUser;
	}
	
	@JsonBackReference
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Long getVideoId() {
		return this.video.getId();
	}


	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}


	@Override
	public String toString() {
		return "ReportVideo [id=" + id + ", status=" + status + ", user=" + user + ", reportType=" + reportType
				+ ", replyUser=" + replyUser + ", video=" + video + "]";
	}

	
	
	
}
