package com.voronkov.Initializr.Dao;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "diler")
    private String diler;

    public ProductDao() {
    }

    public ProductDao(String name, Integer price, String diler, Long id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.diler=diler;
    }

    public String getDiler() {
        return diler;
    }

    public void setDiler(String diler) {
        this.diler = diler;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public ProductDao(String name) {
        this.name = name;
    }

    public ProductDao(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}