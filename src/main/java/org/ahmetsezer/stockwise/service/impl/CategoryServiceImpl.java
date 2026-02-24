package org.ahmetsezer.stockwise.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.CategoryRequest;
import org.ahmetsezer.stockwise.dto.response.CategoryResponse;
import org.ahmetsezer.stockwise.entity.Category;
import org.ahmetsezer.stockwise.exception.ResourceNotFoundException;
import org.ahmetsezer.stockwise.repository.CategoryRepository;
import org.ahmetsezer.stockwise.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CategoryResponse> getAllCategories(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return categoryRepository.findAll(pageable)
                .map(category -> modelMapper.map(category, CategoryResponse.class));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        Category category = modelMapper.map(request, Category.class);

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + id));

        modelMapper.map(request, existingCategory);

        Category updatedCategory = categoryRepository.save(existingCategory);

        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + id));

        category.setActive(false);

        categoryRepository.save(category);
    }
}