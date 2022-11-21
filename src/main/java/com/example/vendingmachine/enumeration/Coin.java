package com.example.vendingmachine.enumeration;

public enum Coin {
    TEN_ST(0.10),
    TWENTY_ST(0.20),
    FIFTY_ST(0.50),
    ONE_LV(1.00),
    TWO_LV(2.00);

    private final Double coin;

    Coin(Double coin) {
        this.coin = coin;
    }

    public Double getCoinValue() {
        return coin;
    }
}
