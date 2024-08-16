package com.tier3Hub.quickCart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(nullable = false,name = "description")
    private String description;

    @Column(name = "image",columnDefinition = "LONGBLOB")
    @Lob
    private String image;

    @Column(name = "status")
    private boolean status;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name="discount")
    private double discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "special_price")
    private double specialPrice;

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
