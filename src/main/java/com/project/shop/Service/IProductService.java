package com.project.shop.Service;

import com.project.shop.Model.Product;

import java.util.List;

public interface IProductService {
    public Product createProduct(Product product);
    public Product editProduct(Product product);
    public void disableProduct(Long id);
    public Product getActiveProduct(Long id);
    public List<Product> getActiveProducts();
    public Product getDisabledProduct(Long id);
    public List<Product> getAllDisabledProducts();
    public List<Product> getActiveProductsByHigherPrice();
    public List<Product> getActiveProductsByLowerPrice();
    public List<Product> getActiveProductsByAlphabeticAZOrder();
    public List<Product> getActiveProductsByAlphabeticZAOrder();
}
