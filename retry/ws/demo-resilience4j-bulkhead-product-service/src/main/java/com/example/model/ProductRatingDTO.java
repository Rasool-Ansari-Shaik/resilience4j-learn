package com.example.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRatingDTO {
    private double rating;
    private String comments;
}
