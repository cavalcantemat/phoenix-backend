package com.ecom.phoenix.controllers;

import com.ecom.phoenix.dtos.ProductToShow;
import com.ecom.phoenix.dtos.ProductsFilter;
import com.ecom.phoenix.dtos.ProductsToPurchase;
import com.ecom.phoenix.models.Product;
import com.ecom.phoenix.services.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping("/list")
    public List<ProductToShow> getFilteredProducts(@RequestBody ProductsFilter filters) {
        return productService.getFilteredProducts(filters);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ProductToShow product) {
        Product savedProduct = this.productService.create(product);

        return ResponseEntity.ok(savedProduct.getId());
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id, @RequestBody ProductToShow product) {
        ResponseEntity<Object> savedProduct = this.productService.edit(id, product);

        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase(@PathVariable String userId, @RequestBody List<ProductsToPurchase> products) throws IOException {
        ResponseEntity<Object> purchase = this.productService.purchase(userId, products);

        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/filterOptions")
    public Map<String, Object> filterOptions() {
        return productService.filterOptions();
    }
}
