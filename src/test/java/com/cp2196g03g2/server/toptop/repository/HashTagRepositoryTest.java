package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.model.HashTagModel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class HashTagRepositoryTest {

	@Autowired
	private IHashTagRepository hashTagRepository;
	
	@Test
	public void testHashTag() {
		HashTag hashTag = hashTagRepository.findByName("TetHungKhoi");
		System.out.println();
	}
	
	@Test
	public void testHashTagCountByView() {
		List<HashTagModel> model = hashTagRepository.selectTotalViewHashTagByName("Tet");
		model.stream().forEach(hashtag -> System.out.println(hashtag.toString()));
	}
}
