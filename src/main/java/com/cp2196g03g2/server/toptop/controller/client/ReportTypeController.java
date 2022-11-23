package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.entity.ReportType;
import com.cp2196g03g2.server.toptop.service.IReportTypeService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/reportType")
public class ReportTypeController {

	@Autowired
	private IReportTypeService reportTypeService;
	
	@GetMapping
	public List<ReportType> findAll(){
		return reportTypeService.findAll();
	}
	
}
