package com.example.service;

import com.example.model.ProductDTO;
import com.example.model.ProductRatingDTO;
import com.example.repository.ProductRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private RestTemplate restTemplate;
    private ProductRepository productRepository;

    public ProductServiceImpl(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    @Value("${rating-service.url}")
    private String ratingServiceUrl;

    @Override
    public ProductDTO getProduct(String id) {
        ProductDTO productDTO = productRepository.getProductById(id);
        List<ProductRatingDTO> ratingList = getProductRating(id);
        productDTO.setRatingsList(ratingList);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<ProductDTO> productsList = productRepository.getAllProducts();
        productsList.forEach(p -> p.setRatingsList(getProductRating(p.getId())));
        return productsList;
    }

//    @Bulkhead(name = "rating-service", fallbackMethod = "getDefaultProductRating", type = Bulkhead.Type.SEMAPHORE)
    @Retry(name = "rating-service", fallbackMethod = "getDefaultProductRating")
    public List<ProductRatingDTO> getProductRating(String id) {
        String reqRatingServiceUrl = ratingServiceUrl + "/" + id;
//        log.info("ratings service url: "+reqRatingServiceUrl);
        log.info("Making a request to " + reqRatingServiceUrl + " at :" + LocalDateTime.now());
        ResponseEntity<List<ProductRatingDTO>> productRatingDTOListRE = restTemplate.exchange(reqRatingServiceUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductRatingDTO>>() {
                });
        List<ProductRatingDTO> productRatingDTOList = productRatingDTOListRE.getBody();
        log.info("Retrieved rating for id {} are: {}", id, productRatingDTOList);
        return productRatingDTOList;
    }

    public List<ProductRatingDTO> getDefaultProductRating(String id, Exception ex) {
        log.warn("fallback method: " + ex.getMessage());
        return new ArrayList<>();
    }
}
