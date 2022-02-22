package com.example.service;

import com.example.model.RatingsDTO;
import com.example.repository.RatingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class RatingsServiceImpl implements RatingsService {

    private RatingsRepository ratingsRepository;

    public RatingsServiceImpl(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @Override
    public List<RatingsDTO> getRatings(String productId) {
        log.info("Ratings required for product id: "+productId);
        List<RatingsDTO> ratingsDTOList = ratingsRepository.getRatingsByProductId(productId);
        log.info("Ratings fetched for product id {} are : {}",productId,ratingsDTOList);
//        return ratingsDTOList;
        if (ThreadLocalRandom.current().nextInt(0,5) == 0){ // Erratic block
            log.error("Erratic");
            throw new org.springframework.web.client.HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ratingsDTOList;
    }
}
