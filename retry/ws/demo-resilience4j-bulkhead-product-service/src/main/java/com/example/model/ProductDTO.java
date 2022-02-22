package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@ToString
public class ProductDTO {
    private String id;
    private String name;
    @Setter
    private List<ProductRatingDTO> ratingsList;
}
