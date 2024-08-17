package com.tier3Hub.quickCart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {

    @NotBlank(message = "Product id is required")
    private Long id;

    @NotBlank(message = "Product name is required")
    @Length(min = 3, message = "Product name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Product description is required")
    @Length(min = 3, message = "Product description must be at least 3 characters long")
    private String description;

    @NotBlank(message = "Product image is required")
    private String image;

    @NotBlank(message = "Product price is required")
    private double price;

    @NotBlank(message = "Product status is required")
    private boolean status;

    private double discount;

    @NotBlank(message = "Product quantity is required")
    private int quantity;

    @NotBlank(message = "Product category is required")
    private int category;

    private double specialPrice;

    private int categoryId;
}
