package com.example.service;

import com.example.model.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProduct(String id);
    List<ProductDTO> getProducts();
}
