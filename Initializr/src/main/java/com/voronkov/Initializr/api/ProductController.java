package com.voronkov.Initializr.api;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.Order;
import com.voronkov.Initializr.converter.ProductConverter;
import com.voronkov.Initializr.dto.ProductDto;
import com.voronkov.Initializr.exceptions.ResourceNotFoundException;
import com.voronkov.Initializr.service.ProductService;
import com.voronkov.Initializr.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator validator;
    private final Order order;

    @GetMapping
    public Page<ProductDto> findBetweenMinMax(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "name", required = false) String name
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, name, page).map(

                p -> productConverter.daoToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        ProductDao productDao = productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Продукт не найден с таким ID " + id));
        return productConverter.daoToDto(productDao);
    }

    @GetMapping("/order/add/{id}")
    public void addProductIntoOrder(@PathVariable(name = "id") Long id) {
        order.addIntoOrder(id);
    }

    @GetMapping("/order/show")
    public List<ProductDto> showProductIntoOrder() {
        return order.getOrderList();
    }

    @GetMapping("/order/remove/{id}")
    public void deleteById(@PathVariable Long id) {
        System.out.println("in controller");
        order.deleteFrom(id);
    }


//    @PutMapping("/base")
//    public ProductDto updateProduct(@RequestBody ProductDto productDtoCl) {
//       validator.validate(productDtoCl);
//        ProductDao p = productService.update(productDtoCl);
//        return productConverter.daoToDto(p);
//    }
//
//    @PostMapping("/base")
//    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
//        validator.validate(productDto);
//        ProductDao p = productConverter.dtoToDao(productDto);
//        p = productService.save(p);
//         return productConverter.daoToDto(p);
//    }
}




