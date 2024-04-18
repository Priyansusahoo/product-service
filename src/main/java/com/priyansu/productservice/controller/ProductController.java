package com.priyansu.productservice.controller;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.dto.ProductResponse;
import com.priyansu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/getProductById")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam String id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/deleteProductById")
    public ResponseEntity<Boolean> deleteProductById(@RequestParam String id){
        return productService.deleteProductById(id);
    }
}
