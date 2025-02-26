package com.project.shop.Controller;

import com.project.shop.Model.Product;
import com.project.shop.Service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Product>> createProduct(@Valid @RequestBody Product product) {

        Map<String, Product> response = new HashMap<>();
        Product productCreated = productService.createProduct(product);

        response.put("product", productCreated);

        URI location = URI.create(String.format("/products/get/%d",  productCreated.getId()));

        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/createbylist")
    public ResponseEntity<Map<String, List<Product>>> createProductsByList(@Valid @RequestBody List<Product> products) {

        Map<String, List<Product>> response = new HashMap<>();
        List<Product> productsCreated = productService.createProductsByList(products);

        response.put("products", productsCreated);

        URI location = URI.create(String.format("/product/getall", products));

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<Map<String, Product>> editProduct(@Valid @RequestBody Product product) {

        Map<String, Product> response = new HashMap<>();
        response.put("product",
                productService.editProduct(product));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        productService.deleteProduct(id);
        response.put("product", "product " + id + " disabled");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/reactivate/{id}")
    public ResponseEntity<Map<String, Product>> reactivateProduct(@PathVariable Long id) {
        Map<String, Product> response = new HashMap<>();
        response.put("product",
                productService.reactivateProduct(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Product>> getProduct(@PathVariable Long id) {
        Map<String, Product> response = new HashMap<>();
        response.put("product", productService.getActiveProduct(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getdisabled")
    public ResponseEntity<Map<String, List<Product>>> getDisabledProducts(@RequestParam (defaultValue = "0") int page,
                                                                          @RequestParam (defaultValue = "10") int size) {
        Map<String, List<Product>> response = new HashMap<>();
        Page<Product> pageableProducts = productService.getAllDisabledProducts(page, size);
        response.put("products", pageableProducts.getContent());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String, List<Product>>> getByFilter(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) Float minPrice,
                                                                  @RequestParam(required = false) Float maxPrice,
                                                                  @RequestParam(required = false) String category,
                                                                  @RequestParam(required = false) boolean orderByHPrice,
                                                                  @RequestParam(required = false) boolean orderByLPrice,
                                                                  @RequestParam(required = false) boolean orderByNameAz,
                                                                  @RequestParam(required = false) boolean orderByNameZa){

        Page<Product> pageProducts =  productService.getByFilter(page, size, name, minPrice, maxPrice, category,
                orderByHPrice, orderByLPrice, orderByNameAz, orderByNameZa);

        Map<String, List<Product>> response = new HashMap<>();
        response.put("response", pageProducts.getContent());

        return ResponseEntity.ok(response);
    }
}