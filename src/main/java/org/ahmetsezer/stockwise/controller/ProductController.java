package org.ahmetsezer.stockwise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.ProductRequest;
import org.ahmetsezer.stockwise.dto.response.ProductResponse;
import org.ahmetsezer.stockwise.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                productService.getAllProducts(page, size)
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response =
                productService.saveProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response =
                productService.editProduct(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchByProductName(
            @RequestParam String name) {

        List<ProductResponse> products =
                productService.searchProductByName(name);

        return ResponseEntity.ok(products);
    }
}