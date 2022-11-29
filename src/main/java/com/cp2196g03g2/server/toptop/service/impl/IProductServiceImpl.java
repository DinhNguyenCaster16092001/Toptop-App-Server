package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.ProductDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Product;
import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.repository.IProductRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IProductService;

@Service
public class IProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public PagableObject<Product> findAllByPage(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		Page<Product> products = productRepository.findAllByPage(request.getKeyword(), pageable);

		List<Product> listOfProducts = products.getContent();

		PagableObject<Product> productsPage = new PagableObject<>();
		productsPage.setData(listOfProducts);
		productsPage.setPageNo(request.getPageNo());
		productsPage.setPageSize(request.getPageSize());
		productsPage.setTotalElements(products.getTotalElements());
		productsPage.setTotalPages(products.getTotalPages());
		productsPage.setLast(products.isLast());

		return productsPage;
	}

	@Override
	public Product saveProduct(ProductDto dto) {
		ApplicationUser user = userRepository.findById(dto.getUserId()).get();
		if(dto.getId() != null)
			return productRepository.save(toEntity(dto, null));
		return productRepository.save(toEntity(dto, user));
	}

	Product toEntity(ProductDto dto, ApplicationUser user) {
		Product product = null;
		if(dto.getId() != null) {
			product = productRepository.findById(dto.getId()).get();
		}else 
			product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setDiscountPrice(dto.getDiscountPrice());
		product.setQty(dto.getQty());
		product.setDescription(dto.getDescription());
		product.setColor(dto.getColor());
		product.setSize(dto.getSize());
		product.setImage(dto.getImage());
		product.setEnable(dto.isEnable());
		if (user != null)
			product.setUser(user);
		return product;
	}

	@Override
	public void deleteProduct(Long id) {
		boolean isExist = productRepository.existsById(id);
		if(isExist)
			productRepository.deleteById(id);
	}

	
}
