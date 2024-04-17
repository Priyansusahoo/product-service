package com.priyansu.productservice.controller;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<Boolean> createProduct(@RequestBody ProductRequest productRequest){
            return productService.createProduct(productRequest);
    }
}
