package com.voronkov.Initializr.dto;

import com.voronkov.Initializr.Dao.ProductDao;

public class ProductDto {
    private Long id;
    private String name;
    private Integer price;

    public ProductDto(ProductDao productDao){
        this.id=productDao.getId();
        this.name=productDao.getName();
        this.price=productDao.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
