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

        // тут у меня проблема, что имя "someName" не подтягивается автоматом в файле html
        // от этого все в теге tr присал в ручную. НО при этом работает.

        return "products";
    }


    // с заданием по добавлению новых продуктов через форму тоже не разобрался.

    // не понимаю что должно вписываться в form th:action="@{/create}"
    // th:object="${product} - тут проде должно быть имя из model.addAttribute метода getForm() ниже, но
    // опять же не подтягивается , показывая ошибку.
    // +
    // th:field="*{id}" - и в этих инпутах тоже в поле что вводить не разобрался. и при запуске ошибка.


//    @GetMapping("/create")
//    public String getForm(Model model){
//        model.addAttribute("product",newProduct);
//        return "products";
//    }
//    @PostMapping("/result")
//    public String create(Product product){
//        productRepository.setNewProduct(product);
//        return "products";
//    }



}
