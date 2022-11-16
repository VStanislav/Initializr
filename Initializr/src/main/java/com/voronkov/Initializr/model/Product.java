package com.voronkov.Initializr.model;


public class Product {
    private int id;
    private final String name;
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String name, int price,int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
