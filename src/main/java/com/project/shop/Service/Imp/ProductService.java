package com.project.shop.Service.Imp;

import com.project.shop.Model.Product;
import com.project.shop.Repository.IProductRepository;
import com.project.shop.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if (product.getPrice() <= 0) return null;
        if (product.getName() == null || product.getName().isEmpty())
            return null;

        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void disableProduct(Long id) {
        Product product =
                productRepository.searchProductByActiveTrueAndId(id);
        product.setActive(false);
    }

    @Override
    public Product getActiveProduct(Long id) {
        return productRepository.searchProductByActiveTrueAndId(id);
    }

    @Override
    public List<Product> getActiveProducts() {
        return productRepository.searchProductsByActiveTrue();
    }

    @Override
    public Product getDisabledProduct(Long id) {
        return productRepository.searchProductByIdAndActiveFalse(id);
    }

    @Override
    public List<Product> getAllDisabledProducts() {
        return productRepository.searchProductsByActiveFalse();
    }

    @Override
    public List<Product> getActiveProductsByHigherPrice() {
        return productRepository.searchProductByActiveTrueOrderByPriceDesc();
    }

    @Override
    public List<Product> getActiveProductsByLowerPrice() {
        return productRepository.searchProductByActiveTrueOrderByPriceDesc();
    }

    @Override
    public List<Product> getActiveProductsByAlphabeticAZOrder() {
        return productRepository.searchProductByActiveTrueOrderByNameAsc();
    }

    @Override
    public List<Product> getActiveProductsByAlphabeticZAOrder() {
        return productRepository.searchProductByActiveTrueOrderByNameDesc();
    }
}
