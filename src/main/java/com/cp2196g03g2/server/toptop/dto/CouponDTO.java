package com.cp2196g03g2.server.toptop.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CouponDTO {
	private Integer id;
	private String code;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@JsonSerialize(as = Date.class)
	private Date createdDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@JsonSerialize(as = Date.class)
	private Date expiredAt;
	private int qty;
	private boolean enable;
	private double value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	} 
	
	
	
}
