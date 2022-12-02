package com.voronkov.Initializr.api;

import com.voronkov.Initializr.Dao.ProductDaoCl;
import com.voronkov.Initializr.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductDaoCl> loadStartList() {
        return productService.getAllProducts();
    }

    @PostMapping("/new")
    public void saveNewProduct(@RequestBody ProductDaoCl productDaoCl) {
        productService.save(productDaoCl);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/change")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        productService.changeScore(studentId, delta);
    }

    @GetMapping("/find")
    public List<ProductDaoCl> findBetweenMinMax(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        System.out.println(min+"+++"+max);
        if (min==null) min=0;
        if (max==null) max=Integer.MAX_VALUE;
        return productService.findProductsBetweenPrice(min,max);
    }
}