package com.cp2196g03g2.server.toptop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.ReportVideoDto;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.service.IVideoService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/management/video")
@PreAuthorize("hasAnyAuthority('ROLE_CONTENT_MODERATOR','ROLE_SUPERADMIN')")
public class VideoAdminController {

	@Autowired
	private IVideoService videoService;
	
	@GetMapping("/{id}")
	public Video findById(@PathVariable Long id) {
		return videoService.findById(id);
	}
	
	
	@PutMapping("/{id}/status/{status}")
	public void updateStatusVideo(@PathVariable Long id, @PathVariable String status) {
		videoService.updateStatusVideo(id, Boolean.parseBoolean(status));
	}
}
