package com.cp2196g03g2.server.toptop.repository.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.internal.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.Role;
import com.cp2196g03g2.server.toptop.repository.IRoleRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRespositoryTest {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Test
	public void testCreateOneRole() {
		Role superAdminRole = new Role("ROLE_SUPER_ADMIN", "Super Admin","Management everything");
		Role savedRole = roleRepository.save(superAdminRole);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testCreateMutilpleRole() {
		Role contentModeratorRole = new Role("ROLE_CONTENT_MODERATOR","Content Censorship Admin", "Content Moderation");
		Role couponModeratorRole = new Role("ROLE_COUPON_MODERATOR","Coupon Management User", "Coupon Moderation");
		Role ticketShopAdminRole = new Role("ROLE_TICKET_MODERATOR", "TopTop Shop Management Admin","Management And Approve Ticket Enable Toptop Shop Feature");
		Role userRole = new Role("ROLE_USER", "Customer","Customer Of Website");
		Role shopUserRole = new Role("ROLE_SHOP_USER", "Shop Management User","Customer Of Website With Toptop Shop Feature");
		List<Role> roles = Arrays.asList(contentModeratorRole, couponModeratorRole, ticketShopAdminRole, userRole, shopUserRole);
		roleRepository.saveAll(roles);
		
		List<Role> dbRoles = roleRepository.findAll();
		
		dbRoles.stream().forEach(System.out::println);
	}
}
