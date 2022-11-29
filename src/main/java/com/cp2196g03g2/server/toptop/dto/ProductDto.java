package com.cp2196g03g2.server.toptop.dto;

public class ProductDto {
	private Long id;
	private String name;
	private double price;
	private double discountPrice;
	private int qty;
	private String description;
	private String color;
	private String size;
	private String image;
	private boolean enable;
	private String userId;


	public ProductDto(Long id, String name, double price, double discountPrice, int qty, String description,
			String color, String size, String image, boolean enable, String userId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.discountPrice = discountPrice;
		this.qty = qty;
		this.description = description;
		this.color = color;
		this.size = size;
		this.image = image;
		this.enable = enable;
		this.userId = userId;
	}
	
	

	public ProductDto(String name, double price, double discountPrice, int qty, String description, String color,
			String size, String image, boolean enable, String userId) {
		this.name = name;
		this.price = price;
		this.discountPrice = discountPrice;
		this.qty = qty;
		this.description = description;
		this.color = color;
		this.size = size;
		this.image = image;
		this.enable = enable;
		this.userId = userId;
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
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
