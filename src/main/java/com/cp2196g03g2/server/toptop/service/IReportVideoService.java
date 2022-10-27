package com.cp2196g03g2.server.toptop.service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ReportVideoDto;
import com.cp2196g03g2.server.toptop.entity.ReportVideo;

public interface IReportVideoService {

	ReportVideo save(ReportVideoDto dto);
	
	PagableObject<ReportVideo> findAllByPage(PagingRequest request);
	
	ReportVideo updateStatusVideo(ReportVideoDto dto);
}
