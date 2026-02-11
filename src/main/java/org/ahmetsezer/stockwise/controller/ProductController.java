package org.ahmetsezer.stockwise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.ProductRequest;
import org.ahmetsezer.stockwise.dto.ProductResponse;
import org.ahmetsezer.stockwise.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest request){

        ProductResponse response = productService.saveProduct(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);

        return ResponseEntity.ok(id + " ID'li ürün başarıyla sistemden silindi.");

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(@Valid @PathVariable Long id,@RequestBody ProductRequest request){

        ProductResponse editedResponse = productService.editProduct(id,request);

        return ResponseEntity.ok(editedResponse);

    }


}
