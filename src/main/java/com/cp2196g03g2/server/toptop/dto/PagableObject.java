package com.cp2196g03g2.server.toptop.dto;

import java.util.List;

public class PagableObject<T> {
	private  List<T> data;
	private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
	public List<T> getData() {
		return data;
	}
	

	public PagableObject() {
	}


	public PagableObject(List<T> data, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
		this.data = data;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}

	


	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPageNo() {
		return pageNo;
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
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
    
    
    
}
