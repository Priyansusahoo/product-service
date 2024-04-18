package com.priyansu.productservice.service;

import com.priyansu.productservice.dto.ProductRequest;
import com.priyansu.productservice.dto.ProductResponse;
import com.priyansu.productservice.model.Product;
import com.priyansu.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Priyansu Sahoo
 * 16-04-2024
 * @Project product-service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
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

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        try {
            List<Product> products = productRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(products.stream().map(product -> mapToProductResponse(product)).toList());
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapToProductResponse(product.get()));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponse());
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteProductById(String id) {
        try {
            Optional<Product> product = productRepository.findById(id);

            if (product.isPresent()) {
                productRepository.deleteById(id);
                return ResponseEntity.ok(Boolean.TRUE);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Boolean.FALSE);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
