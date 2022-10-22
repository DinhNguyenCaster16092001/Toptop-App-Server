package com.cp2196g03g2.server.toptop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IUserService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/management/profile")
public class ProfileController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/{email}")
	public ApplicationUser findById(@PathVariable String email) {
		System.out.println(email);
		return userService.findByEmail(email);
	}
	
	@PutMapping
	public ApplicationUser updateProfile(@RequestBody UserDto userDto) {
		return userService.update(userDto);
	}
}
