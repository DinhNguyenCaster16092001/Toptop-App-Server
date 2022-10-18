package com.cp2196g03g2.server.toptop.controller.client;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class RegisterController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("register")
	public ApplicationUser saveUser(@RequestBody UserDto userDto, HttpServletRequest request) {
		System.out.println(request.getRequestURL().toString());
		return userService.save(userDto);
	}
	


}
