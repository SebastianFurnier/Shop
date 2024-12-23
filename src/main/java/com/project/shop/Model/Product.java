package com.project.shop.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name can't be empty.")
    private String name;
    private String brand;
    private String description;
    @Positive(message = "Price must be greater than zero.")
    private float price;
    private int discount;
    private boolean active;
    @ElementCollection
    private  List<Integer> categories;
    @PositiveOrZero(message = "Stock must be greater than zero.")
    private int stock;
    @OneToOne
    @JoinColumn(name = "principal_pic_id")
    private Photo principalPic;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Photo> photos;
}
