package com.cp2196g03g2.server.toptop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.HeartDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ReportVideoDto;
import com.cp2196g03g2.server.toptop.dto.VideoDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.entity.ReportVideo;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IHashTagRepository;
import com.cp2196g03g2.server.toptop.repository.IReportVideoRepository;
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

	@Autowired
	private IReportVideoRepository reportVideoRepository;

	@Override
	@Transactional
	public Video save(VideoDto videoDto) {
		try {
			List<HashTag> hashTags = new ArrayList<>();

			// Find Hash Tag by name
			for (String hashTag : videoDto.getHashTag()) {
				// if not exist in database then create new one
				if (hashTagRepository.findByName(hashTag) == null) {
					System.out.println(hashTag);
					HashTag savedHashTag = hashTagRepository.save(new HashTag(hashTag));
					// convert array hashtag to hashset
					System.out.println(savedHashTag.toString());
				} else {
					hashTags.add(hashTagRepository.findByName(hashTag));
				}

			}

			// get user in database
			ApplicationUser user = userRepository.findById(videoDto.getUserid())
					.orElseThrow(() -> new NotFoundException("Cannot found user have id" + videoDto.getUserid()));
			Video video = new Video(videoDto.getTitle(), videoDto.getVideoUrl(), videoDto.getMusic(),
					videoDto.isEnableComment(), true, 0L, 0L, user, hashTags);

			// save video into database
			return videoRepository.save(video);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public PagableObject<Video> findAllByPage(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		Page<Video> videos = videoRepository.findAllVideoByPage(pageable, request.getKeyword());

		List<Video> listOfVideos = videos.getContent();

		PagableObject<Video> videoPage = new PagableObject<>();
		videoPage.setData(listOfVideos);
		videoPage.setPageNo(request.getPageNo());
		videoPage.setPageSize(request.getPageSize());
		videoPage.setTotalElements(videos.getTotalElements());
		videoPage.setTotalPages(videos.getTotalPages());
		videoPage.setLast(videos.isLast());

		return videoPage;
	}

	@Override
	@Transactional
	public PagableObject<Video> findFavouriteVideoByPage(PagingRequest request, String userId) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		ApplicationUser user = userRepository.findById(userId).get();

		Page<Video> videos = listToPage(pageable, user.getFavouriteVideos());

		List<Video> listOfVideos = videos.getContent();

		PagableObject<Video> videoPage = new PagableObject<>();
		videoPage.setData(listOfVideos);
		videoPage.setPageNo(request.getPageNo());
		videoPage.setPageSize(request.getPageSize());
		videoPage.setTotalElements(videos.getTotalElements());
		videoPage.setTotalPages(videos.getTotalPages());
		videoPage.setLast(videos.isLast());

		return videoPage;
	}

	@Override
	@Transactional
	public Video updateViewVideo(Long id) {
		Video video = videoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot found video have id" + id));
		Long currentView = video.getView();
		video.setView(currentView + 1);
		return videoRepository.save(video);
	}

	@Override
	@Transactional
	public Video updateHeartVideo(HeartDto dto) {
		Video video = videoRepository.findById(dto.getVideoId())
				.orElseThrow(() -> new NotFoundException("Cannot found video have id" + dto.getVideoId()));
		Long currentHeart = video.getHeart();
		ApplicationUser user = userRepository.findById(dto.getUserId()).get();
		if (dto.isStatus()) {
			video.setView(currentHeart + 1);
			user.addFavouriteVideo(video);
		} else {
			if (user.getFavouriteVideos().contains(video)) {
				user.getFavouriteVideos().remove(video);
				video.setView(currentHeart - 1);
			}
		}
		userRepository.save(user);
		return videoRepository.save(video);
	}

	@Override
	@Transactional
	public Video findById(Long id) {
		return videoRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot found video have id" + id));
	}

	protected Page<Video> listToPage(Pageable pageable, List<Video> entities) {
		int lowerBound = pageable.getPageNumber() * pageable.getPageSize();
		int upperBound = Math.min(lowerBound + pageable.getPageSize(), entities.size());

		List<Video> subList = entities.subList(lowerBound, upperBound);

		return new PageImpl<Video>(subList, pageable, subList.size());
	};

}
