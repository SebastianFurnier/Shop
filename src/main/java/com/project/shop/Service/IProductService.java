package com.project.shop.Service;

import com.project.shop.Model.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    Product editProduct(Product product);
    void deleteProduct(Long id);
    Product getActiveProduct(Long id);
    List<Product> getActiveProducts();
    Product reactivateProduct(Long id);
    List<Product> getAllDisabledProducts();
    List<Product> getByFilter(String name, Float minPrice, Float maxPrice, String category,
                              boolean orderByHPrice, boolean orderByLPrice,
                              boolean orderByNameAz, boolean orderByNamZa);

    List<Product> createProductsByList(List<Product> products);
}
