package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
public class RatingsDTO {
    private String productId;
    private double rating;
    private String comments;
}
