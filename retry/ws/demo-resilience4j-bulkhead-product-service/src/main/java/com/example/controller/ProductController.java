package com.example.controller;

import com.example.model.ProductDTO;
import com.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products/{id}")
    public ProductDTO getProduct(@PathVariable("id") String productId){
        return productService.getProduct(productId);
    }

    @GetMapping("products")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }
}
