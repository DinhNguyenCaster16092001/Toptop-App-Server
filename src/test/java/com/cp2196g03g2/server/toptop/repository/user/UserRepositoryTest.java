package com.cp2196g03g2.server.toptop.repository.user;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Role;
import com.cp2196g03g2.server.toptop.repository.IRoleRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	

	
	@Test
	public void findAllUserTesting() {
		List<ApplicationUser> users = userRepository.findAllByOrderByCreatedDateDesc();
		for (ApplicationUser applicationUser : users) {
			System.out.println(applicationUser.getCreatedDate().toString());
		}
	
	}
	
	/*
	 * @Test public void createUserTesting() { ApplicationUser user = new
	 * ApplicationUser("joesph1994@yahoo.com","9lqWWoAvYRT1Z1Q", "Robert T Outland",
	 * "Wanna lose weight? Try the grapefruit diet. Eat something...follow with half a grapefruit. Eat something else...half a grapefruit. So far today I've had 94 grapefruits."
	 * ); ApplicationUser savedUser = userRepository.save(user); }
	 */
	
	
	/*
	 * @Test public void createMultipleUserTesting() { ApplicationUser user1 = new
	 * ApplicationUser("ismael.breitenbe@hotmail.com","eNHVX8VpCBU6AOs",
	 * "Virginia M. Payan",
	 * "3 reasons why I'm single… Can't date food, can't date celebs, and I can't date the internet."
	 * ); ApplicationUser user2 = new
	 * ApplicationUser("benedict1986@yahoo.com","sQD113loq9bxpPX",
	 * "Abraham J Pogue",
	 * "The art of living does not consist in preserving and clinging to a particular mode of happiness"
	 * ); ApplicationUser user3 = new
	 * ApplicationUser("anjali.row12@hotmail.com","sDxSMRZPQcSHZO0",
	 * "Kathleen J Thaler",
	 * "Devoted reader. Certified travel aficionado. Pop culture advocate. Creator. Entrepreneur."
	 * ); ApplicationUser user4 = new
	 * ApplicationUser("cierra1986@gmail.com","aLdexYDc6HVNlqD", "Walter O Clouse",
	 * "Professional social mediaholic. Award-winning writer. Problem solver. Certified troublemaker."
	 * );
	 * 
	 * List<ApplicationUser> list = new ArrayList<>(); list.add(user1);
	 * list.add(user2); list.add(user3); list.add(user4);
	 * 
	 * userRepository.saveAll(list); }
	 */
	
	
	@Test
	public void updateUpdateUser() {
		String id = "088574c8-5cf6-4d33-aa7f-254d39208280";
		Optional<ApplicationUser> userDb = userRepository.findById(id);
		if(userDb.isPresent()){
			ApplicationUser updateUser = userDb.get();
			updateUser.setHistory("I killed Bigfoot! I also have a few aliens in my basement! You gotta trust me because I said so! Oh by the way I have pictures but you can't look at them!");
			userRepository.save(updateUser);
		}
	}
	
	@Test
	public void AddRoleToUser() {
		String id = "088574c8-5cf6-4d33-aa7f-254d39208280";
		Long roleID  = 1L;
		Optional<ApplicationUser> userDb = userRepository.findById(id);
		Role adminRole = roleRepository.findById(roleID).get();
		if(userDb.isPresent()){
			ApplicationUser updateUser = userDb.get();
			updateUser.setRole(adminRole);
			userRepository.save(updateUser);
		}
	}
	
	
	@Test
	public void UpdateRoleToUser() {
		String id = "088574c8-5cf6-4d33-aa7f-254d39208280";
		Long roleID  = 2L;
		Optional<ApplicationUser> userDb = userRepository.findById(id);
		Role adminRole = roleRepository.findById(roleID).get();
		if(userDb.isPresent()){
			ApplicationUser updateUser = userDb.get();
			updateUser.setRole(adminRole);
			userRepository.save(updateUser);
		}
	}
}
