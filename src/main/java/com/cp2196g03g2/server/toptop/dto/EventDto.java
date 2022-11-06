package com.cp2196g03g2.server.toptop.dto;


public class EventDto {
	private Object value;
	private String type;
	private String requestId;
	private String addressId;
	

	public EventDto(Object value, String type, String requestId, String addressId) {
		this.value = value;
		this.type = type;
		this.requestId = requestId;
		this.addressId = addressId;
	}


	public Object getValue() {
		return value;
	}



	public void setValue(Object value) {
		this.value = value;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getRequestId() {
		return requestId;
	}



	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}



	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	
}
