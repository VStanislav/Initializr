package com.voronkov.Initializr.api;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<com.voronkov.Initializr.dto.ProductDto> findBetweenMinMax(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "diler", required = false) String diler

    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, name, diler, page).map(
                s -> new com.voronkov.Initializr.dto.ProductDto(s)
        );
    }

    @GetMapping("/{id}")
    public ProductDao findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDao productDaoCl) {
        productService.save(productDaoCl);
    }

    @PostMapping
    public void saveNewProduct(@RequestBody ProductDao productDao) {
        productDao.setId(null);
        productService.save(productDao);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}

