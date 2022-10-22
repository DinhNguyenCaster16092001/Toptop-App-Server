package com.cp2196g03g2.server.toptop.dto;

import java.util.Arrays;

public class VideoDto {
	
	private Long id;
	private String title;
	private String videoUrl;
	private String music;
	private boolean enableComment;
	private String userid;
	private String[] hashTag;
	
	
	
	
	public VideoDto() {
	}
	
	
	public VideoDto(Long id, String title, String videoUrl, String music, boolean enableComment, String userid,
			String[] hashTag) {
		this.id = id;
		this.title = title;
		this.videoUrl = videoUrl;
		this.music = music;
		this.enableComment = enableComment;
		this.userid = userid;
		this.hashTag = hashTag;
	}
	public VideoDto(String title, String videoUrl, String music, boolean enableComment, String userid,
			String[] hashTag) {
		this.title = title;
		this.videoUrl = videoUrl;
		this.music = music;
		this.enableComment = enableComment;
		this.userid = userid;
		this.hashTag = hashTag;
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
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public boolean isEnableComment() {
		return enableComment;
	}
	public void setEnableComment(boolean enableComment) {
		this.enableComment = enableComment;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	public String[] getHashTag() {
		return hashTag;
	}
	public void setHashTag(String[] hashTag) {
		this.hashTag = hashTag;
	}
	
	@Override
	public String toString() {
		return "VideoDto [id=" + id + ", title=" + title + ", videoUrl=" + videoUrl + ", music=" + music
				+ ", enableComment=" + enableComment + ", userid=" + userid + ", hashTag=" + Arrays.toString(hashTag)
				+ "]";
	}
	
}
