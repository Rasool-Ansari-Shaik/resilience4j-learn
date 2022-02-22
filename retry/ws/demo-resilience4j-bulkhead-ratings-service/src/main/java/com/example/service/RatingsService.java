package com.example.service;

import com.example.model.RatingsDTO;

import java.util.List;

public interface RatingsService {
    List<RatingsDTO> getRatings(String productId);
}
