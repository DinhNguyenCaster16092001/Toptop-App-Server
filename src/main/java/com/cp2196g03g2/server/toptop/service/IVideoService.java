package com.cp2196g03g2.server.toptop.service;

import com.cp2196g03g2.server.toptop.dto.VideoDto;
import com.cp2196g03g2.server.toptop.entity.Video;

public interface IVideoService {
	Video save(VideoDto videoDto);
}
