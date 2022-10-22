package com.cp2196g03g2.server.toptop.controller.client;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.OtpRequestDto;
import com.cp2196g03g2.server.toptop.dto.ResetPasswordDto;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private IUserService userService;
	
	@PostMapping
	public ApplicationUser saveUser(@RequestBody UserDto userDto, HttpServletRequest request) {
		return userService.saveCustomer(userDto);
	}
	
	@PutMapping("/otp")
	public ApplicationUser verifyOtp(@RequestBody OtpRequestDto otpRequestDto) {
		return userService.activeUserByOtpCode(otpRequestDto.getOtp(), otpRequestDto.getId());
	}
	
	
	@GetMapping("/forgotPassword/{email}")
	public ApplicationUser forgotPassword(@PathVariable String email) {
		return userService.sendOtpCodeByEmail(email);
	}
	
	@PutMapping("/password/reset")
	public ApplicationUser resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		return userService.resetPassword(resetPasswordDto);
	}

}
