package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ReportVideoDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.ReportType;
import com.cp2196g03g2.server.toptop.entity.ReportVideo;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.repository.IReportTypeRepository;
import com.cp2196g03g2.server.toptop.repository.IReportVideoRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;
import com.cp2196g03g2.server.toptop.service.IReportVideoService;

@Service
public class ReportVideoServiceImpl implements IReportVideoService{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IVideoRepository videoRepository;
	
	@Autowired
	private IReportVideoRepository reportVideoRepository;
	
	@Autowired
	private IReportTypeRepository reportTypeRepository;
	

	@Override
	public PagableObject<ReportVideo> findAllByPage(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		Page<ReportVideo> reports = reportVideoRepository.findAllByPage(request.getKeyword(), pageable);

		List<ReportVideo> listOfReportVideos = reports.getContent();

		PagableObject<ReportVideo> reportPage = new PagableObject<>();
		reportPage.setData(listOfReportVideos);
		reportPage.setPageNo(request.getPageNo());
		reportPage.setPageSize(request.getPageSize());
		reportPage.setTotalElements(reports.getTotalElements());
		reportPage.setTotalPages(reports.getTotalPages());
		reportPage.setLast(reports.isLast());

		return reportPage;
	}

	@Override
	@Transactional
	public ReportVideo updateStatusVideo(ReportVideoDto dto) {
		ReportVideo reportVideo = reportVideoRepository.findById(dto.getId()).get();
		Video video = videoRepository.findById(dto.getId()).get();
		reportVideo.setReplyUser(userRepository.findById(dto.getReplyUserId()).get());
		reportVideo.setStatus(true);
		video.setStatus(false);
		videoRepository.save(video);
		return reportVideoRepository.save(reportVideo);
	}

	@Override
	public ReportVideo save(ReportVideoDto dto) {
		try {
			ApplicationUser user = userRepository.findById(dto.getUserId()).get();
			Video video = videoRepository.findById(dto.getVideoId()).get();
			ReportType type = reportTypeRepository.findById(dto.getTypeId()).get();
		
			ReportVideo reportVideo = new ReportVideo(false, user, type, null, video);
			
			return reportVideoRepository.save(reportVideo);
		}catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}



}
