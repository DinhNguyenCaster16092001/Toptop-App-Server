package com.cp2196g03g2.server.toptop.entity;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tbl_comment")
public class Comment {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column
	private String content;
	
	@Column(nullable = false, updatable = false)
	@JsonSerialize(as = LocalDateTime.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss dd-MM-yyyy")
	private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private ApplicationUser user;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "videoid")
	private Video video;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Comment parent;
	
	@Transient
	private int childrenTotal;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<Comment> children = new ArrayList<>();
	

	public Comment() {
	}

	public Comment(String content, ApplicationUser user, Video video, Comment parent) {
		this.content = content;
		this.user = user;
		this.video = video;
		this.parent = parent;
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


	public ApplicationUser getUser() {
		return user;
	}

	
	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	@JsonBackReference
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@JsonBackReference
	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	@JsonBackReference
	public List<Comment> getChildren() {
		return children;
	}

	public void setChildren(List<Comment> children) {
		this.children = children;
	}
	
	

	public int getChildrenTotal() {
		return this.children.size();
	}

	public void setChildrenTotal(int childrenTotal) {
		this.childrenTotal = childrenTotal;
	}
	
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createdDate=" + createdDate + ", user=" + user
				+ ", video=" + video + ", parent=" + parent + ", children=" + children + "]";
	}
	
}
