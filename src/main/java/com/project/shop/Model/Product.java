package com.project.shop.Model;

import jakarta.persistence.*;
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
    private String name;
    private String brand;
    private String description;
    private float price;
    private int discount;
    private boolean active;
    @ElementCollection
    private  List<Integer> categories;
    private int stock;
    @OneToOne
    @JoinColumn(name = "principal_pic_id")
    private Photo principalPic;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Photo> photos;
}
