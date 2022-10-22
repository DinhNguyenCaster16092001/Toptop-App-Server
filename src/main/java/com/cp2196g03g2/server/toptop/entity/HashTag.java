package com.cp2196g03g2.server.toptop.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_hashtag")
public class HashTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 225, nullable = false, unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "hashTags", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Video> videos;

	
	
	public HashTag() {
	}


	public HashTag(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public HashTag(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Video> getVideos() {
		return videos;
	}


	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}


	@Override
	public String toString() {
		return "HashTag [id=" + id + ", name=" + name + ", videos=" + videos + "]";
	}
	
	

}
