package com.cp2196g03g2.server.toptop.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.VideoDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IHashTagRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;
import com.cp2196g03g2.server.toptop.service.IVideoService;

@Service
public class VideoServiceImpl implements IVideoService {

	@Autowired
	private IHashTagRepository hashTagRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IVideoRepository videoRepository;

	@Override
	@Transactional
	public Video save(VideoDto videoDto) {
		try {
			List<HashTag> hashTags = new ArrayList<>();
			
			//Find Hash Tag by name
			for (String hashTag : videoDto.getHashTag()) {
				//if not exist in database then create new one
				if(hashTagRepository.findByName(hashTag) == null) 
				{
				 System.out.println(hashTag);
				 HashTag savedHashTag = hashTagRepository.save(new HashTag(hashTag));
				//convert array hashtag to hashset
				 System.out.println(savedHashTag.toString());
				}else {
					hashTags.add(hashTagRepository.findByName(hashTag));
				}
				
			}
			
				//get user in database
				ApplicationUser user = 
						userRepository.findById(videoDto.getUserid())
						.orElseThrow(()-> new NotFoundException("Cannot found user have id" + videoDto.getUserid()));
				Video video = new Video(videoDto.getTitle(),
						videoDto.getVideoUrl(), 
						videoDto.getMusic(), videoDto.isEnableComment(), true, 
						0, user, hashTags);

				//save video into database
				return videoRepository.save(video);
		}catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

}
