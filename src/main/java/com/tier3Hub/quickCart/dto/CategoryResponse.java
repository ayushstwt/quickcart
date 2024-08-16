package com.tier3Hub.quickCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private int id;
    private String name;
    private String description;
    private String image;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
