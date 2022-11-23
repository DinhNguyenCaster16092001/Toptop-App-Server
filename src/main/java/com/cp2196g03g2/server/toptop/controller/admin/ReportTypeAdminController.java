package com.cp2196g03g2.server.toptop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1/management/reportType")
@PreAuthorize("hasAnyAuthority('ROLE_CONTENT_MODERATOR','ROLE_SUPERADMIN')")
public class ReportTypeAdminController {

	@Autowired
	private IReportTypeService reportTypeService;
	
	@GetMapping
	public List<ReportType> findAll(){
		return reportTypeService.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ReportType findById(@PathVariable Integer id) {
		return reportTypeService.findById(id);
	}
	
	
	@PostMapping
	public ReportType save(@RequestBody String description) {
		return reportTypeService.save(description);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		reportTypeService.deleteById(id);
	}
}
