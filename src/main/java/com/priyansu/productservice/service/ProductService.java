package com.priyansu.productservice.service;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.model.Product;
import com.priyansu.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ResponseEntity<Boolean> createProduct(ProductRequest productRequest) {
        try {
            if (productRequest.getName() == null || productRequest.getName().isEmpty() ||
                    productRequest.getDescription() == null || productRequest.getDescription().isEmpty() ||
                    productRequest.getPrice() == null ){

                log.info("Getting parameter(s) null or empty");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Boolean.FALSE);

            } else{
                Product newProduct = Product.builder()
                        .name(productRequest.getName())
                        .description(productRequest.getDescription())
                        .price(productRequest.getPrice())
                        .build();

                productRepository.save(newProduct);
                log.info("New product with id {} is created", newProduct.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
