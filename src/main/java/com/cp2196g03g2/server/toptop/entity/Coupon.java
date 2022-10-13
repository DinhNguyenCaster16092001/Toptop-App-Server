package com.cp2196g03g2.server.toptop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_coupon")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String code;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date createdDate;

	
	@Column
	private Date expiredAt;
	
	@Column(name = "quantity")
	private Integer qty;
	
	
	@Column(name = "enable")
	private boolean enable;
	
	
	@Column(name = "value")
	private double value;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "coupon_user", 
			  joinColumns = @JoinColumn(name = "coupon_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<ApplicationUser> users = new ArrayList<>();
	

	public Coupon(Integer id, String code, Date createdDate, Date expiredAt, Integer qty, boolean enable,
			double value) {
		this.id = id;
		this.code = code;
		this.createdDate = createdDate;
		this.expiredAt = expiredAt;
		this.qty = qty;
		this.enable = enable;
		this.value = value;
	}

	
	public Coupon(String code, Date expiredAt, Integer qty, double value) {
		this.code = code;
		this.expiredAt = expiredAt;
		this.qty = qty;
		this.value = value;
	}




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


	public Integer getQty() {
		return qty;
	}


	public void setQty(Integer qty) {
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
	
	

	@JsonBackReference
	public List<ApplicationUser> getUsers() {
		return users;
	}


	public void setUsers(List<ApplicationUser> users) {
		this.users = users;
	}


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", code=" + code + ", createdDate=" + createdDate + ", expiredAt=" + expiredAt
				+ ", qty=" + qty + ", enable=" + enable + ", value=" + value + "]";
	}
	
}
