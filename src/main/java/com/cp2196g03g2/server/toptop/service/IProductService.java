package com.cp2196g03g2.server.toptop.service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.Product;

public interface IProductService {
	PagableObject<Product> findAllByPage(PagingRequest request, String alias);
}
