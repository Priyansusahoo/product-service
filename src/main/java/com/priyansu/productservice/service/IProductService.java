package com.priyansu.productservice.service;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.dto.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Priyansu Sahoo
 * 17-04-2024
 * @Project product-service
 */
public interface IProductService {
    ResponseEntity<Boolean> createProduct(ProductRequest productRequest);
    ResponseEntity<List<ProductResponse>> getAllProduct();
}
