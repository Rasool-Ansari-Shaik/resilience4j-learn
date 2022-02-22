package com.example.repository;

import com.example.model.ProductDTO;
import com.example.model.ProductRatingDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProductRepository {

    List<ProductDTO> productDTOList = Arrays.asList(
            ProductDTO.builder().id("111").name("one").build(),
            ProductDTO.builder().id("222").name("two").build()
    );

    public ProductDTO getProductById(String id){
        return productDTOList.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ProductDTO> getAllProducts(){
        return productDTOList;
    }
}
