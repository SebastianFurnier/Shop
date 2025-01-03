package com.project.shop.Controller;

import com.project.shop.ExceptionHandler.ResourceNotFoundException;
import com.project.shop.Model.Product;
import com.project.shop.Service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

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
        response.put("product",
                productService.createProduct(product));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createbylist")
    public ResponseEntity<Map<String, List<Product>>> createProductsByList(@Valid @RequestBody List<Product> products) {

        Map<String, List<Product>> response = new HashMap<>();
        response.put("product",
                productService.createProductsByList(products));

        return ResponseEntity.ok(response);
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

    @GetMapping("/getall")
    public ResponseEntity<Map<String, List<Product>>> getActiveProducts() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProducts());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getdisabled")
    public ResponseEntity<Map<String, List<Product>>> getDisabledProducts() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getAllDisabledProducts());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String, List<Product>>> getByFilter(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) Float price,
                                                                  @RequestParam(required = false) String category) {

        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getByFilter(name, price, category));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbyhprice")
    public ResponseEntity<Map<String, List<Product>>> getProductsByHighPrice() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByHigherPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbylprice")
    public ResponseEntity<Map<String, List<Product>>> getProductsByLowPrice() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByLowerPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbyascname")
    public ResponseEntity<Map<String, List<Product>>> getProductsByAscName() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByAlphabeticAZOrder());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbydescname")
    public ResponseEntity<Map<String, List<Product>>> getProductsByDescName() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByAlphabeticZAOrder());

        return ResponseEntity.ok(response);
    }
}