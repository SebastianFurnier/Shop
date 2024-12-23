package com.project.shop.Controller;

import com.project.shop.Model.Product;
import com.project.shop.Service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put("error", error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Product>> createProduct(@Valid @RequestBody Product product){

        Map<String, Product> response = new HashMap<>();
        response.put("product",
                productService.createProduct(product));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<Map<String, Product>> editProduct(@RequestBody Product product){

        Map<String, Product> response = new HashMap<>();
        response.put("product",
                productService.editProduct(product));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        productService.disableProduct(id);
        response.put("Product " + id + ":", " disabled");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Product>> getProduct(@PathVariable Long id){
        Map<String, Product> response = new HashMap<>();
        response.put("product", productService.getActiveProduct(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getall")
    public ResponseEntity<Map<String, List<Product>>> getActiveProducts(){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProducts());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getdisabled")
    public ResponseEntity<Map<String, List<Product>>> getDisabledProducts(){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getAllDisabledProducts());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/{name}&{price}&{category}")
    public ResponseEntity<Map<String, List<Product>>> getByFilter(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) float price,
                                                                  @RequestParam(required = false) String category){

        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getByFilter(name, price, category));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbyhprice")
    public ResponseEntity<Map<String, List<Product>>> getProductsByHighPrice(){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByHigherPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbylprice")
    public ResponseEntity<Map<String, List<Product>>> getProductsByLowPrice(){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByLowerPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbyascname")
    public ResponseEntity<Map<String, List<Product>>> getProductsByAscName(){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByAlphabeticAZOrder());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getbydescname")
    public ResponseEntity<Map<String, List<Product>>> getProductsByDescName(){
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", productService.getActiveProductsByAlphabeticZAOrder());

        return ResponseEntity.ok(response);
    }
}