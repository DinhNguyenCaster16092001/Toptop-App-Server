package com.cp2196g03g2.server.toptop.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tbl_user")
public class ApplicationUser {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(insertable = true)
	private String id;
	
	@Column(name = "email",
			updatable = false,
			unique = true, 
			nullable = false, 
			length = 300)
	private String email;
	
	@Column(name = "password",
			length = 300)
	@JsonIgnore
	private String password;
	
	@Column(length = 225, unique = true, updatable = true)
	private String alias;
	
	@Column(name = "avatar", length = 300, nullable = true)
	private String avatar;
	
	@Column(name = "full_name", length = 256, nullable = false)
	private String fullName;
	
	@Column(name = "history", columnDefinition = "TEXT")
	private String history;
	
	@CreationTimestamp
	@Column(name = "created_date",nullable = false, updatable = false)
	@JsonSerialize(as = Date.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date createdDate;
	
	
	@Column(name = "one_time_password")
	private String OTP;
	
	@Column(name = "otp_requested_time")
	private Date otpRequestedTime;
	
	@Column(name = "is_active", nullable = false)
	private boolean isActive;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "role_id")
	private Role role;

	
	@OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL, mappedBy = "user")	
	private List<TicketShop> ticketsShops = new ArrayList<>();
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL, mappedBy = "user")	
	private List<Product> products = new ArrayList<>();
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Coupon> coupons;
	
	public ApplicationUser() {

	}

	public ApplicationUser(String id, String email, String password, String alias, String avatar, String fullName,
			String history) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.alias = alias;
		this.avatar = avatar;
		this.fullName = fullName;
		this.history = history;
	}

	public ApplicationUser(String email, String password, String alias, String avatar, String fullName,
			String history) {
		this.email = email;
		this.password = password;
		this.alias = alias;
		this.avatar = avatar;
		this.fullName = fullName;
		this.history = history;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@JsonBackReference
	public List<TicketShop> getTicketsShops() {
		return ticketsShops;
	}
	
	
	@JsonBackReference
	public Set<Coupon> getCoupons() {
		return coupons;
	}
	
	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void setTicketsShops(List<TicketShop> ticketsShops) {
		this.ticketsShops = ticketsShops;
	}
	
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}
	

	@JsonBackReference
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", email=" + email + ", password=" + password + ", alias=" + alias
				+ ", avatar=" + avatar + ", fullName=" + fullName + ", history=" + history + ", createdDate="
				+ createdDate + ", isActive=" + isActive + "]";
	}

	
}
