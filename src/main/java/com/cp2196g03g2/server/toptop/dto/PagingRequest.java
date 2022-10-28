package com.cp2196g03g2.server.toptop.dto;

public class PagingRequest {
	private int pageNo;
	private int pageSize;
	private String sortBy;
	private String sortDir;
	private String keyword;
	private int isActive;
	
	
	
	
	public PagingRequest() {
	}
	
	
	public PagingRequest(int pageNo, int pageSize, String sortBy, String sortDir, String keyword, int isActive) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sortBy = sortBy;
		this.sortDir = sortDir;
		this.keyword = keyword;
		this.isActive = isActive;
	}
	

	public PagingRequest(int pageNo, int pageSize, String keyword) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.keyword = keyword;
	}


	public PagingRequest(int pageNo, int pageSize, String sortBy, String sortDir, String keyword) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sortBy = sortBy;
		this.sortDir = sortDir;
		this.keyword = keyword;
	}
	

	public PagingRequest(int pageNo, int pageSize, String sortBy, String sortDir) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sortBy = sortBy;
		this.sortDir = sortDir;
	}


	public int getPageNo() {
		return pageNo - 1;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	
}
