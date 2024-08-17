package com.tier3Hub.quickCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private String image;
    private double price;
    private boolean status;
    private double discount;
    private int quantity;
    private double specialPrice;
    private int category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
