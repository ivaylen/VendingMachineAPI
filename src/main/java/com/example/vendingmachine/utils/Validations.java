package com.example.vendingmachine.utils;

import com.example.vendingmachine.enumeration.ProductType;
import com.example.vendingmachine.exception.BadRequestException;
import com.example.vendingmachine.exception.CoinsNotFoundException;
import com.example.vendingmachine.exception.ProductIdAlreadyExistsException;
import com.example.vendingmachine.exception.ProductNotFoundException;
import com.example.vendingmachine.model.OrderProduct;
import com.example.vendingmachine.model.Product;
import com.example.vendingmachine.repository.InventoryRepository;

import java.util.List;

public class Validations {

    private static void validateAmount(List<Product> list, OrderProduct orderProduct) {
        // Validation for amount if enough
        if(orderProduct.sumAllCoins() != orderProduct.getPriceForType(list)) {
            throw new CoinsNotFoundException("Amount is less or more than the product price: " + orderProduct.sumAllCoins());
        }
    }

    private static void validateProductQuantity(InventoryRepository inventoryRepository, OrderProduct orderProduct) {
        // Validation for Product if enough quantity
        long count = inventoryRepository.retrieveAll().stream()
                .filter(e -> e.getType().equals(orderProduct.getType())).count();  // Condition here
        if(count < 1) {
            throw new ProductNotFoundException("Product is Not Found: " + orderProduct.getType());
        }
    }

    public static void validateOrderProduct(InventoryRepository inventoryRepository, OrderProduct orderProduct) {
        validateProductQuantity(inventoryRepository, orderProduct);
        validateAmount(inventoryRepository.retrieveAll(), orderProduct);
    }

    public static void validateAddProductCount(InventoryRepository inventoryRepository, ProductType productType) {
        long count = inventoryRepository.retrieveAll()
                .stream()
                .filter(e -> e.getType().equals(productType))
                .count();
        if (count >= 10) {
            throw new BadRequestException("Machine can hold up to 10 products of the same type: " + productType);
        }
    }

    public static ProductType validateProductType(String orderType) {
        ProductType type = ProductType.findProductByType(orderType);
        if(type != null) {
            return type;
        } else {
            throw new ProductNotFoundException("Product is Not Found: " + orderType);
        }
    }

    public static void validateId(InventoryRepository inventoryRepository, Long id) {
        long count = inventoryRepository.retrieveAll().stream().filter(e -> e.getId().equals(id)).count();
        if (count != 1) {
            throw new ProductNotFoundException(
                    "Product with id " + id + " does not exists");
        }
    }

    public static void validateIdExists(InventoryRepository inventoryRepository, Long id) {
        long count = inventoryRepository.retrieveAll().stream().filter(e -> e.getId().equals(id)).count();
        if (count > 0) {
            throw new ProductIdAlreadyExistsException(
                    "Product already exists");
        }
    }
}
