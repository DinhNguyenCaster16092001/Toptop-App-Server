package com.cp2196g03g2.server.toptop.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", length = 256, nullable = false)
	private String title;
	
	@Column(name = "video_url", nullable = false)
	private String url;
	
	@Column(name = "music", nullable = false)
	private String musicUrl;
	
	@Column(name = "enable_comment")
	private boolean enableComment;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "view")
	private Long view;
	
	@Column(name = "heart")
	private Long heart;
	
	@Column(name = "is_public")
	private Boolean professed;
	
	@Transient
	private Long comment;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private ApplicationUser user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			  name = "tbl_videos_hashtag", 
			  joinColumns = @JoinColumn(name = "video_id"), 
			  inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
	private List<HashTag> hashTags = new ArrayList<>();

	
	public Video() {
	}


	public Video(Long id, String title, String url, String musicUrl,boolean enableComment, boolean status) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.musicUrl = musicUrl;
		this.enableComment = enableComment;
		this.status = status;
	}

	

	public Video(Long id, String title, String url, String musicUrl, boolean enableComment, boolean status, Long view,
			Long heart, boolean professed, List<HashTag> hashTags) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.musicUrl = musicUrl;
		this.enableComment = enableComment;
		this.status = status;
		this.view = view;
		this.heart = heart;
		this.professed = professed;
		this.hashTags = hashTags;
	}


	public Video(Long id, String title, String url, String musicUrl, boolean enableComment, boolean status, Long view,
			Long heart, boolean professed, Long comment, ApplicationUser user, List<HashTag> hashTags) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.musicUrl = musicUrl;
		this.enableComment = enableComment;
		this.status = status;
		this.view = view;
		this.heart = heart;
		this.professed = professed;
		this.comment = comment;
		this.user = user;
		this.hashTags = hashTags;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public boolean isEnableComment() {
		return enableComment;
	}

	public void setEnableComment(boolean enableComment) {
		this.enableComment = enableComment;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	public Long getView() {
		return view;
	}


	public void setView(Long view) {
		this.view = view;
	}

	
	public ApplicationUser getUser() {
		return user;
	}


	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	@JsonBackReference
	public List<HashTag> getHashTags() {
		return hashTags;
	}


	public void setHashTags(List<HashTag> hashTags) {
		this.hashTags = hashTags;
	}


	public void addHashTag(HashTag hashTag) {
		this.hashTags.add(hashTag);
	}


	public Long getHeart() {
		return heart;
	}


	public void setHeart(Long heart) {
		this.heart = heart;
	}

	
	
	@Transient
	public Long getComment() {
		return comment;
	}

	@Transient
	public void setComment(Long comment) {
		this.comment = comment;
	}
	
	public Boolean isProfessed() {
		return professed;
	}


	public void setProfessed(Boolean professed) {
		this.professed = professed;
	}


	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", url=" + url + ", musicUrl=" + musicUrl + ", enableComment="
				+ enableComment + ", status=" + status + ", view=" + view + ", heart=" + heart + ", user=" + user
				+ ", hashTags=" + hashTags + "]";
	}


}
