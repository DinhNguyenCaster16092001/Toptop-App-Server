package com.cp2196g03g2.server.toptop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.entity.Video;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CommentRepositoryTest {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IVideoRepository videoRepository;
	
	@Autowired
	private ICommentRepository commentRepository;
	
	@Test
	public void testAddComment() {
		Video video = videoRepository.findById(20L).get();
		ApplicationUser user = userRepository.findById("0890a192-cd2e-4c37-90dd-2ab7bbd674ae").get();
	
		Comment comment = new Comment("t sợ luôn lên xh rồi nề kk, bẹp trong vd đây nha mng =))", new java.util.Date(), user, video, null);
		Comment savedComment = commentRepository.save(comment);
		assertThat(savedComment.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testAddChildComment() {
		Video video = videoRepository.findById(20L).get();
		ApplicationUser user = userRepository.findById("0890a192-cd2e-4c37-90dd-2ab7bbd674ae").get();
		Comment parentComment = commentRepository.findById(1L).get();
		
		
		Comment comment = new Comment("ko má nào chịu thua má nào ha", new java.util.Date(), user, video, parentComment);
		Comment savedComment = commentRepository.save(comment);
		assertThat(savedComment.getId()).isGreaterThan(0);
	}
}
