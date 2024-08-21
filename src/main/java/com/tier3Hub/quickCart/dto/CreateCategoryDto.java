package com.tier3Hub.quickCart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDto {

    @NotBlank(message = "Category name is required")
    private String name;

    @NotBlank(message = "Category description is required")
    private String description;

    @NotBlank(message = "Category image is required")
    private String image;

    private boolean status;
}
