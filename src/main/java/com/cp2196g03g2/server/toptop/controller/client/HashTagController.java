package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.model.HashTagModel;
import com.cp2196g03g2.server.toptop.service.IHashTagService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/hashtag")
public class HashTagController {

	@Autowired
	private IHashTagService hashTagService;
	
	@GetMapping
	public List<HashTagModel> findByName(@RequestParam String name) {
		return hashTagService.findByName(name);
	}
}
