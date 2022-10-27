package com.cp2196g03g2.server.toptop.controller.client;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.BooleanResult;
import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.OtpRequestDto;
import com.cp2196g03g2.server.toptop.dto.ResetPasswordDto;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.service.IUserService;
import com.cp2196g03g2.server.toptop.service.impl.EmailServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@PostMapping
	public ApplicationUser saveUser(@RequestBody UserDto userDto, HttpServletRequest request) {
		ApplicationUser user = userService.saveCustomer(userDto);
		if(user != null) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("otp", user.getOTP());
			emailServiceImpl.sendMail(user.getEmail(), "OTP Code", "otp-code", model);
		}
		return user;
	}
	
	@PutMapping("/otp")
	public ApplicationUser verifyOtp(@RequestBody OtpRequestDto otpRequestDto) {
		return userService.activeUserByOtpCode(otpRequestDto.getOtp(), otpRequestDto.getId());
	}
	
	@GetMapping("/alias")
	public BooleanResult existAlias(@RequestParam(value = "target", required = true) String alias, @RequestParam(value = "id", required = false) String id) {
		ObjectKey objectKey = new ObjectKey(alias, id);
		return new BooleanResult(userService.findByAlias(objectKey));
	}

	@GetMapping("/email")
	public BooleanResult existEmail(@RequestParam(value = "target", required = true) String email, @RequestParam(value = "id", required = false) String id) {
		ObjectKey objectKey = new ObjectKey(email, id);
		return new BooleanResult(userService.findByEmail(objectKey));
	}
	
	
	@GetMapping("/forgotPassword/{email}")
	public ApplicationUser forgotPassword(@PathVariable String email) {
		ApplicationUser user = userService.sendOtpCodeByEmail(email);
		if(user != null) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("otp", user.getOTP());
			emailServiceImpl.sendMail(user.getEmail(), "OTP Code", "otp-code", model);
		}
		return user;
	}
	
	@PutMapping("/password/reset")
	public ApplicationUser resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		return userService.resetPassword(resetPasswordDto);
	}

}
