package com.cp2196g03g2.server.toptop.repository.hashtag;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.repository.IHashTagRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class HashTagRepositoryTest {
	
	@Autowired
	private IHashTagRepository hashTagRepository;
	
	@Autowired
	private IVideoRepository videoRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void createHashtagTest() {
		HashTag tag = new HashTag("monan");
		hashTagRepository.save(tag);
	}
	
	@Test
	public void createVideoWithHashTag() {
		String uid = "9c00c0ec-63d5-4958-87a5-e734469d03f4";
		ApplicationUser user = userRepository.findById(uid).get();
		HashTag tag = hashTagRepository.findById(1L).get();
		Video video = new 
				Video("Trứng chiên mắm k bỏ ớt nhưng lại cay rớt nác mắt ", "https://cdn.filestackcontent.com/k3nUPrYRmvBpqtewTn7g","Something", true, true);
		video.setUser(user);
		video.addHashTag(tag);
		Video videoSaved = videoRepository.save(video);
		
		assertThat(videoSaved.getId()).isGreaterThan(0);
	}
}
