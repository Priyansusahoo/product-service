package com.priyansu.productservice.controller;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Priyansu Sahoo
 * 16-04-2024
 * @Project product-service
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("createProduct")
    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productRequest){
        try {
            productService.createProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(productRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productRequest);
        }
    }
}
