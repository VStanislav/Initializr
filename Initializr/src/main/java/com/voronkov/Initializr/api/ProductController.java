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
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public String getProduct(@PathVariable int id){
        Product product = productRepository.getProductList().stream()
                .filter(it -> Objects.equals(id,it.getId()))
                .findFirst().orElse(null);

        assert product != null;
        return product.toString();
    }

    @GetMapping("/all")
    public String getProducts(Model model){
        model.addAttribute("someName",productRepository.getProductList());

        // тут у меня проблема, что имя "someName" не подтягивается автоматом в файле html
        // от этого все в теге tr присал в ручную. НО при этом работает.

        return "products";
    }

    @GetMapping("/create")
    public String create(@RequestParam Integer id,
                         @RequestParam String name,
                         @RequestParam Integer price){

        Product product = new Product(name,price,id);
        productRepository.setNewProduct(product);
        return "redirect:/all";
    }



}
