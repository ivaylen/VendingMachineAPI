package com.example.vendingmachine.service.implementation;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.enumeration.ProductType;
import com.example.vendingmachine.model.OrderProduct;
import com.example.vendingmachine.model.Product;
import com.example.vendingmachine.repository.InventoryRepository;
import com.example.vendingmachine.service.VendingMachineService;
import com.example.vendingmachine.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private final InventoryRepository inventoryRepository;
    private OrderProduct orderProduct;

    @Autowired
    public VendingMachineServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.orderProduct = new OrderProduct();
    }

    public Product saveProduct(Product product) {
        // Validation for Max 10 count for Product type
        Validations.validateAddProductCount(inventoryRepository, product.getType());
        return inventoryRepository.add(product);
    }

    public List<Product> getAllProducts() {
        return inventoryRepository.retrieveAll();
    }

    public Product updateProduct(Product product) {
        //Validate ID
        Validations.validateId(inventoryRepository, product.getId());
        return inventoryRepository.update(product);
    }

    public Product deleteProduct(Long id) {
        //Validate ID
        Validations.validateId(inventoryRepository, id);
        return inventoryRepository.remove(id);
    }

    @Override
    public List<Coin> insertCoin(Coin coin) {
        this.orderProduct.addCoins(coin);
        return this.orderProduct.getCoins();
    }

    @Override
    public List<Coin> reset() {
        List<Coin> removedCoins = this.orderProduct.getCoins();
        this.orderProduct.clearAllCoins();
        this.orderProduct.setType(null);
        return removedCoins;
    }

    @Override
    public OrderProduct buyProduct(String orderType) {
        // Validate input product type
        ProductType type = Validations.validateProductType(orderType);
        this.orderProduct.setType(type);

        // Validate input
        Validations.validateOrderProduct(inventoryRepository, this.orderProduct);

        // Remove bought product
        List<Product> list = inventoryRepository.retrieveAll();
        list.stream()
                .filter(e -> e.getType().equals(orderProduct.getType()))  // Condition here
                .findFirst()
                .ifPresent(list::remove);

        // Convert list to map
        Map<Long, Product> newProductList = new HashMap<>();
        for (Product product : list) {
            newProductList.put(product.getId(), product);
        }
        inventoryRepository.setRepository(newProductList);
        orderProduct.clearAllCoins();
        return orderProduct;
    }

}
