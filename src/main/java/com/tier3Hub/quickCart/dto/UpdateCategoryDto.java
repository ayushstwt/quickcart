package com.tier3Hub.quickCart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCategoryDto {

    @NotBlank(message = "Category id name is required")
    private int id;

    @NotBlank(message = "Category name is required")
    private String name;

    @NotBlank(message = "Category description is required")
    private String description;

    @NotBlank(message = "Category image is required")
    private String image;

    @NotBlank(message = "Category status is required")
    private boolean status;

}
