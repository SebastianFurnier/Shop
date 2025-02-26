package com.project.shop.Service;

import com.project.shop.Model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    Product editProduct(Product product);
    void deleteProduct(Long id);
    Product getActiveProduct(Long id);
    Product reactivateProduct(Long id);
    Page<Product> getAllDisabledProducts(int page, int size);
    Page<Product> getByFilter(int page, int size, String name, Float minPrice, Float maxPrice, String category,
                              boolean orderByHPrice, boolean orderByLPrice,
                              boolean orderByNameAz, boolean orderByNamZa);

    List<Product> createProductsByList(List<Product> products);
}
