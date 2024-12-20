package com.project.shop.Service.Imp;

import com.project.shop.Model.Product;
import com.project.shop.Repository.IProductRepository;
import com.project.shop.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> product =
                productRepository.getProductByActiveAndId(true, id);

        Product myProduct = product.get();
        myProduct.setActive(false);

        productRepository.save(myProduct);
    }

    @Override
    public Product getActiveProduct(Long id) {
        Optional<Product> myProduct =
                productRepository.getProductByActiveAndId(true, id);

        return myProduct.orElse(null);
    }

    @Override
    public List<Product> getActiveProducts() {
        return productRepository.getProductsByActive(true);
    }

    @Override
    public Product getDisabledProduct(Long id) {
        Optional<Product> myProduct = productRepository.getProductByActiveAndId(false, id);

        return myProduct.orElse(null);
    }

    @Override
    public List<Product> getAllDisabledProducts() {
        return productRepository.getProductsByActive(false);
    }

    @Override
    public List<Product> getActiveProductsByHigherPrice() {
        return productRepository.getProductsByActiveOrderByPriceDesc(true);
    }

    @Override
    public List<Product> getActiveProductsByLowerPrice() {
        return productRepository.getProductsByActiveOrderByPriceAsc(true);
    }

    @Override
    public List<Product> getActiveProductsByAlphabeticAZOrder() {
        return productRepository.getProductsByActiveOrderByNameAsc(true);
    }

    @Override
    public List<Product> getActiveProductsByAlphabeticZAOrder() {
        return productRepository.getProductsByActiveOrderByNameDesc(true);
    }
}
