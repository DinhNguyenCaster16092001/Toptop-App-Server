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
import com.cp2196g03g2.server.toptop.entity.Product;
import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.repository.IProductRepository;
import com.cp2196g03g2.server.toptop.service.IProductService;

@Service
public class IProductServiceImpl implements IProductService{

	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public PagableObject<Product> findAllByPage(PagingRequest request, String alias) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(request.getSortBy()).ascending()
                : Sort.by(request.getSortBy()).descending();
	 
	 	// create Pageable instance
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);
        
  
      
        Page<Product> products = productRepository.findAllByPage(request.getKeyword(), alias, pageable);

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

}
