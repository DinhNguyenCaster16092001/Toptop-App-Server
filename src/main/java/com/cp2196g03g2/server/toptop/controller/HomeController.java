package com.cp2196g03g2.server.toptop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.exception.NotFoundException;

@RestController
public class HomeController {
	
	@GetMapping("/test")
	public String sayHello() {
		throw new NotFoundException("***************Exception Occured************ ");
	}
}
