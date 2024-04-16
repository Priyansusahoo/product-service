package com.priyansu.productservice.repository;

import com.priyansu.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Priyansu Sahoo
 * 16-04-2024
 * @Project product-service
 */
public interface ProductRepository extends MongoRepository<Product, String> {
}
