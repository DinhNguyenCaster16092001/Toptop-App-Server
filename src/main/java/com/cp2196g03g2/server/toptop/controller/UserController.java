package com.cp2196g03g2.server.toptop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.BooleanResult;
import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.service.IUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@GetMapping
	public List<ApplicationUser> findAll(){
		return userService.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ApplicationUser findById(@PathVariable String id){
		return userService.findById(id)	;
	}
	
	@PostMapping
	public ApplicationUser saveUser(@RequestBody UserDto userDto){
		return userService.save(userDto);
	}
	
	
	@PutMapping("/{id}")
	public ApplicationUser updateUser(@RequestBody UserDto userDto, @PathVariable String id){
		return userService.update(userDto, id);
	}
	
	@GetMapping("/alias")
	public BooleanResult existAlias(@RequestBody ObjectKey objectKey){
		return new BooleanResult(userService.findByAlias(objectKey));
	}
	
	
	@GetMapping("/email")
	public BooleanResult existEmail(@RequestBody ObjectKey objectKey){
		return new BooleanResult(userService.findByEmail(objectKey));
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id){
		userService.delete(id);
	}

	
}
