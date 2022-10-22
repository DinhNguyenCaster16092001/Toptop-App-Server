package com.cp2196g03g2.server.toptop.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 265, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@Column(name = "discount_price")
	private double discountPrice;
	
	@Column(name = "quantity", nullable = false)
	private int qty;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column
	private String color;
	
	@Column
	private String size;
	
	@Column 
	private boolean enable;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private ApplicationUser user;
	
	
	public Product() {
	}

	public Product(String name, double price, double discountPrice, int qty, String description, String color,
			String size) {
		this.name = name;
		this.price = price;
		this.discountPrice = discountPrice;
		this.qty = qty;
		this.description = description;
		this.color = color;
		this.size = size;
	}

	public Product(Long id, String name, double price, double discountPrice, int qty, String description, String color,
			String size, boolean enable) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.discountPrice = discountPrice;
		this.qty = qty;
		this.description = description;
		this.color = color;
		this.size = size;
		this.enable = enable;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", discountPrice=" + discountPrice
				+ ", qty=" + qty + ", description=" + description + ", color=" + color + ", size=" + size + ", enable="
				+ enable + "]";
	}
	
}
