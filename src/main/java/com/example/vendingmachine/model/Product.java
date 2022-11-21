package com.example.vendingmachine.model;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.enumeration.ProductType;

import java.io.Serializable;

public class Product implements Serializable {
    private Long id;
    private String name;
    private ProductType type;
    private Coin coin;

    public Product() {}

    public Product(Long id, String name, ProductType type, Coin coin) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.coin = coin;
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

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + coin +
                '}';
    }

}
