package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.entity.ReportType;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IReportTypeRepository;
import com.cp2196g03g2.server.toptop.service.IReportTypeService;

@Service
public class ReportTypeServiceImpl implements IReportTypeService{

	
	@Autowired
	private IReportTypeRepository reportTypeRepository;
	
	@Override
	public List<ReportType> findAll() {
		return reportTypeRepository.findAll();
	}

	@Override
	public ReportType save(String description) {
		ReportType reportType = new ReportType(description);
		return reportTypeRepository.save(reportType);
	}

	@Override
	public ReportType findById(Integer id) {
		return reportTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot found report type " + id));
	}

	@Override
	public void deleteById(Integer id) {
		boolean isExist = reportTypeRepository.existsById(id);
		if(isExist)
			reportTypeRepository.deleteById(id);
	}

	
}
