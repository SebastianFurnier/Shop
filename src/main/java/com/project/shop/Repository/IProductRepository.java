package com.project.shop.Repository;

import com.project.shop.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR p.name LIKE %:name%) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:category IS NULL OR :category MEMBER OF p.categories) AND" +
            "(:active IS false OR p.active = :active)")
    Page<Product> findByFilters(Pageable pageable,
                                @Param("name") String name,
                                @Param("minPrice") Float minPrice,
                                @Param("maxPrice") Float maxPrice,
                                @Param("category") String category,
                                @Param("active") boolean active);
}