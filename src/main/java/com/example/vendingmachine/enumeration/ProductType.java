package com.example.vendingmachine.enumeration;

public enum ProductType {
    COCA_COLA("COCA_COLA"),
    SPRITE("SPRITE"),
    COFFEE("COFFEE");

    private final String typeValue;

    ProductType(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getType() {
        return typeValue;
    }

    public static ProductType findProductByType(String productTypeValue) {
        ProductType result = null;
        for(ProductType type : values()) {
            if(type.getType().equalsIgnoreCase(productTypeValue)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
