package org.ahmetsezer.stockwise.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.ProductRequest;
import org.ahmetsezer.stockwise.dto.ProductResponse;
import org.ahmetsezer.stockwise.entity.Category;
import org.ahmetsezer.stockwise.entity.Product;
import org.ahmetsezer.stockwise.repository.CategoryRepository;
import org.ahmetsezer.stockwise.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

    public ProductResponse saveProduct(ProductRequest request) {
        Product product = modelMapper.map(request, Product.class);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı!"));
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silinmek istenen ürün bulunamadı! ID: " + id));

        productRepository.delete(product);
    }

    public ProductResponse editProduct(Long id, ProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("ID: " + id + " olan ürün bulunamadı!")));

        modelMapper.map(request, existingProduct);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı!"));
            existingProduct.setCategory(category);
        }

        Product editedProduct = productRepository.save(existingProduct);
        return modelMapper.map(editedProduct, ProductResponse.class);
    }
}
