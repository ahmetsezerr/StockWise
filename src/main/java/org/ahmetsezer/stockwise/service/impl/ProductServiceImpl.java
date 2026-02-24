package org.ahmetsezer.stockwise.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.ProductRequest;
import org.ahmetsezer.stockwise.dto.response.ProductResponse;
import org.ahmetsezer.stockwise.entity.Category;
import org.ahmetsezer.stockwise.entity.Product;
import org.ahmetsezer.stockwise.exception.ResourceNotFoundException;
import org.ahmetsezer.stockwise.repository.CategoryRepository;
import org.ahmetsezer.stockwise.repository.ProductRepository;
import org.ahmetsezer.stockwise.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ProductResponse> getAllProducts(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable)
                .map(product -> modelMapper.map(product, ProductResponse.class));
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {

        Product product = modelMapper.map(request, Product.class);

        if (request.getCategoryId() != null) {

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id: " + request.getCategoryId()));

            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));

        product.setActive(false);

        productRepository.save(product);
    }

    @Override
    public ProductResponse editProduct(Long id, ProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));

        modelMapper.map(request, existingProduct);

        if (request.getCategoryId() != null) {

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id: " + request.getCategoryId()));

            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);

        return modelMapper.map(updatedProduct, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> searchProductByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword must not be empty");
        }

        String trimmedName = name.trim();

        List<Product> products =
                productRepository.findByNameContainingIgnoreCase(trimmedName);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No products found matching: " + trimmedName
            );
        }

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }
}