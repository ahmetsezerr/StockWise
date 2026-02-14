package org.ahmetsezer.stockwise.controller;


import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.CategoryRequest;
import org.ahmetsezer.stockwise.dto.CategoryResponse;
import org.ahmetsezer.stockwise.dto.ProductRequest;
import org.ahmetsezer.stockwise.dto.ProductResponse;
import org.ahmetsezer.stockwise.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){

        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request){

        CategoryResponse savedResponse = categoryService.saveCategory(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable Long id,@RequestBody CategoryRequest request){

        CategoryResponse editedResponse = categoryService.editCategory(id,request);

        return ResponseEntity.ok(editedResponse);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory (@PathVariable Long id){

       categoryService.deleteCategory(id);

        return ResponseEntity.ok(id + " ID'li ürün başarıyla sistemden silindi.");
    }




    }

