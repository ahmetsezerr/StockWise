package org.ahmetsezer.stockwise.service;

import org.ahmetsezer.stockwise.dto.request.CategoryRequest;
import org.ahmetsezer.stockwise.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Page<CategoryResponse> getAllCategories(int page,int size);

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
