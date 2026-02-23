package org.ahmetsezer.stockwise.service;

import org.ahmetsezer.stockwise.dto.request.ProductRequest;
import org.ahmetsezer.stockwise.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ProductResponse> getAllProducts(int page,int size);

    List<ProductResponse> searchProductByName(String name);

    ProductResponse saveProduct(ProductRequest request);

    ProductResponse editProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
