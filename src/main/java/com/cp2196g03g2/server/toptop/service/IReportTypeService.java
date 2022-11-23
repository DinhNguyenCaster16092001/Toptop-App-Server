package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.entity.ReportType;

public interface IReportTypeService {
	List<ReportType> findAll();
	ReportType save(String description);
	ReportType findById(Integer id);
	void deleteById(Integer id);
}
