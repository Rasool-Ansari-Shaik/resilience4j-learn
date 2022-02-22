package com.example.controller;

import com.example.model.RatingsDTO;
import com.example.service.RatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
@Slf4j
public class RatingsController {

    private RatingsService ratingsService;

    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GetMapping("ratings/{id}")
    public List<RatingsDTO> getRatings(@PathVariable("id") String productId){
        List<RatingsDTO> ratingsDTOList = ratingsService.getRatings(productId);
        return ratingsDTOList;
    }






}
