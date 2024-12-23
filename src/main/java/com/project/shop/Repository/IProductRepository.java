package com.project.shop.Repository;

import com.project.shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getProductByActiveAndId(boolean active, Long id);
    List<Product> getProductsByActive(boolean active);
    List<Product> getProductsByActiveOrderByPriceAsc(boolean active);
    List<Product> getProductsByActiveOrderByPriceDesc(boolean active);
    List<Product> getProductsByActiveOrderByNameAsc(boolean active);
    List<Product> getProductsByActiveOrderByNameDesc(boolean active);
    //List<Product> getByFilter(String name, float price, String category);

}
