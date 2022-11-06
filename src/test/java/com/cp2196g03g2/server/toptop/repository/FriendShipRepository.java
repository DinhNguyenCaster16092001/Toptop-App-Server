package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.FriendShip;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class FriendShipRepository {

	
	@Autowired
	private IFriendShipRepository friendShipRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void createFriendShip() {
		String requestUser = "331e8961-a53c-4731-90eb-a10e4b73896a";
		String acceptUser = "c130082f-611d-4bbb-bb73-d411d25af6a5";
		FriendShip friendShip = new FriendShip(userRepository.findById(requestUser).get(), 
				userRepository.findById(acceptUser).get(), true);
		
		friendShipRepository.save(friendShip);
	}
	
	
	@Test
	public void getListFriend() {
		String id = "0a40a3c7-cbc5-4696-af88-359f31de5a89";
		List<String> idFriend = friendShipRepository.findAllListFriend(id);
		List<ApplicationUser> users = userRepository.findAllById(idFriend);
		for (ApplicationUser applicationUser : users) {
			System.out.println(applicationUser.toString());
		}
	}
}
