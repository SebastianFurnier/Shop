package com.project.shop.Repository;

import com.project.shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.id = :id")
    Product searchProductByActiveTrueAndId(Long id);
    @Query("SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> searchProductsByActiveTrue();
    @Query("SELECT p FROM Product p WHERE p.isActive = false AND p.id = :id")
    Product searchProductByIdAndActiveFalse(Long id);
    @Query("SELECT p FROM Product p WHERE p.isActive = false")
    List<Product> searchProductsByActiveFalse();
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.price ASC")
    List<Product> searchProductByActiveTrueOrderByPriceAsc();
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.price DESC")
    List<Product> searchProductByActiveTrueOrderByPriceDesc();
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.name ASC")
    List<Product> searchProductByActiveTrueOrderByNameAsc();
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.name DESC")
    List<Product> searchProductByActiveTrueOrderByNameDesc();

}
