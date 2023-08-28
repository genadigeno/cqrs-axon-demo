package com.example.axondemo.controller;

import com.example.axondemo.aggregate.model.ProductDTO;
import com.example.axondemo.query.model.ProductDAO;
import com.example.axondemo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/inventory")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.OK);
    }

    @PostMapping("/stock")
    public ResponseEntity<String> addStock(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.addStock(productDTO), HttpStatus.OK);
    }

    @GetMapping("/{page}/{pageSize}")
    public CompletableFuture<List<ProductDAO>> getProducts(@PathVariable int page, @PathVariable int pageSize) {
        return productService.getProducts(page, pageSize);
    }
}