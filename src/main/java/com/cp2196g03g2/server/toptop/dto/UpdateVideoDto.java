package com.cp2196g03g2.server.toptop.dto;

public class UpdateVideoDto {
	private Long id;
	private boolean enableComment;
	private boolean professed;
	
	public UpdateVideoDto() {
	}

	public UpdateVideoDto(Long id, boolean enableComment, boolean professed) {
		this.id = id;
		this.enableComment = enableComment;
		this.professed = professed;
	}
	
	public UpdateVideoDto(boolean enableComment, boolean professed) {
		this.enableComment = enableComment;
		this.professed = professed;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
