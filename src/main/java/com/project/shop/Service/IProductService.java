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
    List<Product> getActiveProductsByHigherPrice();
    List<Product> getActiveProductsByLowerPrice();
    List<Product> getActiveProductsByAlphabeticAZOrder();
    List<Product> getActiveProductsByAlphabeticZAOrder();
    List<Product> getByFilter(String name, float price, String category);
    List<Product> createProductsByList(List<Product> products);
}
