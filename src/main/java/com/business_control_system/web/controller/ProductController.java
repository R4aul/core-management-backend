package com.business_control_system.web.controller;

import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.dto.UpdateProductRequest;
import com.business_control_system.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int elements
    ){
       return ResponseEntity.ok(this.productService.getAll(page, elements));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(
            @RequestBody @Valid CreateProductRequest request
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(request));
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> getById(@PathVariable(name = "productId") int id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(
            @PathVariable int id,
            @RequestBody @Valid UpdateProductRequest request
            ){
        return ResponseEntity.ok(this.productService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable int id
    ){

        return ResponseEntity.ok(this.productService.delete(id));
    }

}
