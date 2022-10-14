package com.cp2196g03g2.server.toptop.repository.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Product;
import com.cp2196g03g2.server.toptop.repository.IProductRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Test
	public void createProduct() {
		String uid = "739b62da-5e23-4302-9931-94532c250cda";
		ApplicationUser user = userRepository.findById(uid).get();
		Product product = new Product("LAPTOP SIÊU CẤP", 100, 90, 10, "sOMETHING WENT RIGHT", null, null);
		product.setUser(user);
		productRepository.save(product);
	}
}
