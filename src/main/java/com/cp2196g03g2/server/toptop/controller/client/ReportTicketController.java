package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.ReportVideoDto;
import com.cp2196g03g2.server.toptop.entity.ReportVideo;
import com.cp2196g03g2.server.toptop.service.IReportVideoService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/report")
public class ReportTicketController {

	@Autowired
	private IReportVideoService iReportVideoService;
	
	@PostMapping
	public ReportVideo createReportVideo(ReportVideoDto dto) {
		return iReportVideoService.save(dto);
	}
}
