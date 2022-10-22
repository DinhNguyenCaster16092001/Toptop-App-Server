package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.VideoDto;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.service.IHashTagService;
import com.cp2196g03g2.server.toptop.service.IVideoService;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/video")
public class VideoController {
	
	@Autowired
	private IVideoService videoService;
	

	
	@GetMapping("/watch")
	public String findAll() {
		return "hello";
	}
	
	
	@PostMapping("/interactive")
	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_SHOP_USER')")
	public Video createVideo(@RequestBody VideoDto videoDto) {
		return videoService.save(videoDto);
	}
	
	
}
