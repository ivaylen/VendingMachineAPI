package com.example.vendingmachine.model;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.enumeration.ProductType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderProduct implements Serializable {
    private ProductType type;
    private List<Coin> coins;

    public OrderProduct() {
        this.type = null;
        this.coins = new ArrayList<>();
    }

    public OrderProduct(ProductType type, List<Coin> coins) {
        this.type = type;
        this.coins = coins;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public void addCoins(Coin coin) {
        this.coins.add(coin);
    }

    public void clearAllCoins() {
        this.coins.clear();
    }

    public double sumAllCoins() {
        double sum = 0.0;
        for(Coin coin : coins) {
            sum += coin.getCoinValue();
        }
        return sum;
    }

    public double getPriceForType(List<Product> products) {
        for(Product product : products) {
            if(product.getType() == this.getType()) {
                return product.getCoin().getCoinValue();
            }
        }
        return 0.0;
    }
}
