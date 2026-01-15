package com.business_control_system.web.controller;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.domain.dto.CategoryRequest;
import com.business_control_system.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Category>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int elements){
        return ResponseEntity.ok(this.service.getAll(page,elements));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") int categoryId){
       return ResponseEntity.ok(this.service.getById(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(
            @PathVariable("id") int categoryId,
            @RequestBody @Valid CategoryRequest request){
        return ResponseEntity.ok(this.service.update(categoryId, request));
    }
}
