package com.project.shop.Service.Imp;

import com.project.shop.ExceptionHandler.ResourceNotFoundException;
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
    public Product createProduct(Product product)  {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product =
                productRepository.getProductByActiveAndId(true, id);

        if (product.isEmpty())
            throw new ResourceNotFoundException("This product doesn't exist or this is an invalid ID.");

        Product myProduct = product.get();
        myProduct.setActive(false);

        productRepository.save(myProduct);
    }

    @Override
    public Product getActiveProduct(Long id) {
        return productRepository
                .getProductByActiveAndId(true, id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " is not active or does not exist."));
    }

    @Override
    public List<Product> getActiveProducts() {
        return productRepository.getProductsByActive(true);
    }

    @Override
    public Product reactivateProduct(Long id) {
        Optional<Product> myProduct = productRepository.getProductByActiveAndId(false, id);

        if (myProduct.isEmpty())
            throw new ResourceNotFoundException("This product doesn't exist or this is an invalid ID.");

        Product product = myProduct.get();
        product.setActive(true);

        return productRepository.save(product);
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

    @Override
    public List<Product> getByFilter(String name, float price, String category) {
        return null; //productRepository.getByFilter(name, price, category);
    }
}