package com.cp2196g03g2.server.toptop.service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ReportVideoDto;
import com.cp2196g03g2.server.toptop.dto.VideoDto;
import com.cp2196g03g2.server.toptop.entity.Video;

public interface IVideoService {
	Video save(VideoDto videoDto);
	
	PagableObject<Video> findAllByPage(PagingRequest request);
	Video updateViewVideo(Long id);
	Video findById(Long id);
}
