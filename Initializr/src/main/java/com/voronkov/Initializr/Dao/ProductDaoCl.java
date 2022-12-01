package com.voronkov.Initializr.Dao;


import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductDaoCl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    public ProductDaoCl() {
    }

    public ProductDaoCl(String name, Integer price,Long id) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public ProductDaoCl(String name) {
        this.name = name;
    }

    public ProductDaoCl(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}