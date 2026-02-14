package org.ahmetsezer.stockwise.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.CategoryRequest;
import org.ahmetsezer.stockwise.dto.CategoryResponse;
import org.ahmetsezer.stockwise.entity.Category;
import org.ahmetsezer.stockwise.repository.CategoryRepository;
import org.ahmetsezer.stockwise.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<CategoryResponse> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> modelMapper.map(category, CategoryResponse.class))
                .toList();
    }


    public CategoryResponse saveCategory(CategoryRequest request) {

        Category category = modelMapper.map(request, Category.class);

        Category savedCategory = categoryRepository.save(category);

        CategoryResponse response = modelMapper.map(savedCategory, CategoryResponse.class);

        return response;

    }


    public CategoryResponse editCategory(Long id, CategoryRequest request) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("ID: " + id + " olan kategori bulunamadı!")));

        modelMapper.map(request, existingCategory);

        Category editedCategory = categoryRepository.save(existingCategory);

        return modelMapper.map(editedCategory, CategoryResponse.class);

    }
    @Transactional
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silinmek istenen kategori bulunamadı!"));

        categoryRepository.delete(category);
    }
}