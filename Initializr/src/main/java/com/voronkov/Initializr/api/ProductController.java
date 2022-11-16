package com.voronkov.Initializr.api;

import com.voronkov.Initializr.model.Product;
import com.voronkov.Initializr.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    Product newProduct;

    @GetMapping("/{id}")
    @ResponseBody
    public String getProduct(@PathVariable int id){
        Product product = productRepository.getProductList().stream()
                .filter(it -> Objects.equals(id,it.getId()))
                .findFirst().orElse(null);

        assert product != null;
        return product.toString();
    }

    @GetMapping
    public String getProducts(Model model){
        model.addAttribute("someName",productRepository.getProductList());
        return "products";
    }

    @PostMapping("/create")
    @ResponseBody
    public String getForm(Model model){
        model.addAttribute("product",newProduct);
        return "products";
    }
    @PostMapping("/result")
    @ResponseBody
    public String create(Product product){
        productRepository.setNewProduct(product);
        return "products";
    }



}
