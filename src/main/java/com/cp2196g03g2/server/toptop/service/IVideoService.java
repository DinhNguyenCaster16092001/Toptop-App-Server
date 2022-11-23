package com.cp2196g03g2.server.toptop.service;

import com.cp2196g03g2.server.toptop.dto.HeartDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.UpdateVideoDto;
import com.cp2196g03g2.server.toptop.dto.VideoDto;
import com.cp2196g03g2.server.toptop.entity.Video;

public interface IVideoService {
	Video save(VideoDto videoDto);
	
	PagableObject<Video> findAllByPage(PagingRequest request);
	Video updateViewVideo(Long id);
	Video updateHeartVideo(HeartDto dto);
	Video updateShareVideo(Long id);
	Video updateEnableCommentProfessedVideo(UpdateVideoDto dto);
	Video findById(Long id);
	boolean isUserHeartBefore(Long videoId, String userId);
	PagableObject<Video> findVideoByUserId(PagingRequest request, String userId, boolean professed);	
	PagableObject<Video> findFavouriteVideoByPage(PagingRequest request, String userId);
	void deleteVideoById(Long id);
}
