package com.voronkov.Initializr.api;

import com.voronkov.Initializr.Dao.ProductDaoCl;
import com.voronkov.Initializr.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDaoCl> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        productService.changeScore(studentId, delta);
    }
}