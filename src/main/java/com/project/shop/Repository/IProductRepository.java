package com.project.shop.Repository;

import com.project.shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Product searchProductByActiveTrueAndId(Long id);
    List<Product> searchProductsByActiveTrue();
    Product searchProductByActiveFalseAndId(Long id);
    List<Product> searchProductsByActiveFalse();
    List<Product> searchProductByActiveTrueOrderByPriceAsc();
    List<Product> searchProductByActiveTrueOrderByPriceDesc();
    List<Product> searchProductByActiveTrueOrderByNameAsc();
    List<Product> searchProductByActiveTrueOrderByNameDesc();

}
