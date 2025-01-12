package com.project.shop.Repository;

import com.project.shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR p.name LIKE %:name%) AND " +
            "(:price IS NULL OR p.price >= :price) AND " +
            "(:category IS NULL OR :category MEMBER OF p.categories) AND" +
            "(:active IS false OR p.active = :active)")
    List<Product> findByFilters(@Param("name") String name,
                                @Param("price") Float price,
                                @Param("category") String category,
                                @Param("active") boolean active);
}