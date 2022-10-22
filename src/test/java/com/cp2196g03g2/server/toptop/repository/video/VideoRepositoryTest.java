package com.cp2196g03g2.server.toptop.repository.video;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class VideoRepositoryTest {

	@Autowired
	private IVideoRepository videoRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void createVideoTest() {
		Video video = new 
				Video("Trứng chiên mắm k bỏ ớt nhưng lại cay rớt nác mắt ", "https://cdn.filestackcontent.com/k3nUPrYRmvBpqtewTn7g","Something", true, true);
		Video videoSaved = videoRepository.save(video);
		assertThat(videoSaved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createVideoByUserTest() {
		String uid = "9c00c0ec-63d5-4958-87a5-e734469d03f4";
		ApplicationUser user = userRepository.findById(uid).get();
		Video video = new 
				Video("Trứng chiên mắm k bỏ ớt nhưng lại cay rớt nác mắt ", "https://cdn.filestackcontent.com/k3nUPrYRmvBpqtewTn7g","Something", true, true);
		video.setUser(user);
		Video videoSaved = videoRepository.save(video);
		
		assertThat(videoSaved.getId()).isGreaterThan(0);
	}
	
}
