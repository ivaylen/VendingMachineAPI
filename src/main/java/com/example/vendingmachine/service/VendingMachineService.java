package com.example.vendingmachine.service;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.model.OrderProduct;

import java.util.List;

public interface VendingMachineService {
    List<Coin> insertCoin(Coin coin);
    List<Coin> reset();
    OrderProduct buyProduct(String orderType);
}
