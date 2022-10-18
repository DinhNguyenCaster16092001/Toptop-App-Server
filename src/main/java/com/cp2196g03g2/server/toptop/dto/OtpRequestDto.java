package com.cp2196g03g2.server.toptop.dto;

public class OtpRequestDto {
	String id;
	String otp;

	public OtpRequestDto(String id, String otp) {
		this.id = id;
		this.otp = otp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
