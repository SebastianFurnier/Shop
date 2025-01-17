package com.project.shop.Service.Imp;

import com.project.shop.ExceptionHandler.ResourceNotFoundException;
import com.project.shop.Model.Product;
import com.project.shop.Repository.IProductRepository;
import com.project.shop.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    public Page<Product> getByFilter(int page, int size, String name, Float minPrice, Float maxPrice, String category,
                                     boolean orderByHPrice, boolean orderByLPrice,
                                     boolean orderByNameAz, boolean orderByNamZa) {

        if (minPrice == null)
            minPrice = 0f;

        if (maxPrice == null)
            maxPrice = Float.MAX_VALUE;

        Sort sort = Sort.unsorted();

        if (orderByHPrice) {
            sort = Sort.by(Sort.Direction.DESC, "price");
        } else if (orderByLPrice) {
            sort = Sort.by(Sort.Direction.ASC, "price");
        } else if (orderByNameAz) {
            sort = Sort.by(Sort.Direction.ASC, "name");
        } else if (orderByNamZa) {
            sort = Sort.by(Sort.Direction.DESC, "name");
        }

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findByFilters(pageable, name,
                minPrice, maxPrice, category, true);

        /*if (orderByHPrice) {
            productList.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        }else if (orderByLPrice) {
            productList.sort(Comparator.comparingDouble(Product::getPrice));
        }else if (orderByNameAz) {
            productList.sort((p1, p2) -> CharSequence.compare(p1.getName(), p2.getName()));
        }else if (orderByNamZa) {
            productList.sort((p1, p2) -> CharSequence.compare(p2.getName(), p1.getName()));
        }
        */
    }

    @Override
    public List<Product> createProductsByList(List<Product> products) {

        Product productAux;

        for(Product product : products){
            productAux = createProduct(product);

            if (productAux == null)
                throw new ResourceNotFoundException("Server can't save following product: " + product.getName());
        }

        return products;
    }
}