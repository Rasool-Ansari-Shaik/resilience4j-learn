package com.example.repository;

import com.example.model.RatingsDTO;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RatingsRepository {
    List<RatingsDTO> ratingsDTOList = Arrays.asList(
            RatingsDTO.builder().productId("111").rating(1.23).comments("below average product").build(),
            RatingsDTO.builder().productId("111").rating(2.34).comments("average product").build(),
            RatingsDTO.builder().productId("222").rating(3.23).comments("good product").build(),
            RatingsDTO.builder().productId("444").rating(4.34).comments("better product").build()
    );

    public List<RatingsDTO> getRatingsByProductId(String productId){
        return ratingsDTOList.stream()
                .filter(r -> productId.equals(r.getProductId()))
                .collect(Collectors.toList());
    }

}
