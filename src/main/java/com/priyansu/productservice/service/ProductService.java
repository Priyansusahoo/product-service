package com.priyansu.productservice.service;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.model.Product;
import com.priyansu.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Priyansu Sahoo
 * 16-04-2024
 * @Project product-service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        try {
            Product newProduct = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            productRepository.save(newProduct);

            log.info("New product with id {} created", newProduct.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
