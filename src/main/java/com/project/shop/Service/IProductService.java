package com.project.shop.Service;

import com.project.shop.Model.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    Product editProduct(Product product);
    void disableProduct(Long id) throws Exception;
    Product getActiveProduct(Long id);
    List<Product> getActiveProducts();
    Product getDisabledProduct(Long id);
    List<Product> getAllDisabledProducts();
    List<Product> getActiveProductsByHigherPrice();
    List<Product> getActiveProductsByLowerPrice();
    List<Product> getActiveProductsByAlphabeticAZOrder();
    List<Product> getActiveProductsByAlphabeticZAOrder();
}
