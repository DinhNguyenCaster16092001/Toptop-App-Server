package com.cp2196g03g2.server.toptop.dto;

import java.util.Arrays;

public class VideoDto {

	private Long id;
	private String title;
	private String videoUrl;
	private boolean enableComment;
	private boolean professed;
	private String userid;
	private String[] hashTag;

	public VideoDto() {
	}

	

	public VideoDto(Long id, String title, String videoUrl, boolean enableComment, boolean professed,
			String userid, String[] hashTag) {
		this.id = id;
		this.title = title;
		this.videoUrl = videoUrl;
		this.enableComment = enableComment;
		this.professed = professed;
		this.userid = userid;
		this.hashTag = hashTag;
	}


	

	public VideoDto(String title, String videoUrl, boolean enableComment, boolean professed,
			String userid, String[] hashTag) {
		this.title = title;
		this.videoUrl = videoUrl;
		this.enableComment = enableComment;
		this.professed = professed;
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

	public boolean isEnableComment() {
		return enableComment;
	}

	public void setEnableComment(boolean enableComment) {
		this.enableComment = enableComment;
	}

		
	public boolean isProfessed() {
		return professed;
	}


	public void setProfessed(boolean professed) {
		this.professed = professed;
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
		return "VideoDto [id=" + id + ", title=" + title + ", videoUrl=" + videoUrl + ", enableComment=" + enableComment
				+ ", professed=" + professed + ", userid=" + userid + ", hashTag=" + Arrays.toString(hashTag) + "]";
	}


	

}
