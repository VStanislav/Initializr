package com.voronkov.Initializr;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.converter.ProductConverter;
import com.voronkov.Initializr.dto.ProductDto;
import com.voronkov.Initializr.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Order {
    private List<ProductDto> orderList = new ArrayList<>();
    private final ProductService productService;
    private final ProductConverter productConverter;

    public void addIntoOrder(Long id){
        ProductDao productDao = productService.findById(id).orElseThrow();
        orderList.add(productConverter.daoToDto(productDao));
    }


    public void deleteFrom(Long id) {
        System.out.println("in");
        ProductDao productDao = productService.findById(id).orElseThrow();
        orderList.remove(productConverter.daoToDto(productDao));
    }
}
