package com.example.vendingmachine.resource;

import com.example.vendingmachine.enumeration.Coin;
import com.example.vendingmachine.model.OrderProduct;
import com.example.vendingmachine.model.Product;
import com.example.vendingmachine.service.implementation.VendingMachineServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class VendingMachineController {
    private final VendingMachineServiceImpl vendingMachineServiceImpl;

    public VendingMachineController(VendingMachineServiceImpl vendingMachineServiceImpl) {
        this.vendingMachineServiceImpl = vendingMachineServiceImpl;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return vendingMachineServiceImpl.saveProduct(product);
    }

    @GetMapping("/list")
    public List<Product> findAllProducts() {
        return vendingMachineServiceImpl.getAllProducts();
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return vendingMachineServiceImpl.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
        return vendingMachineServiceImpl.deleteProduct(id);
    }

    @GetMapping("/insert/{coin}")
    public List<Coin> insertCoins(@PathVariable("coin") Coin coin) {
        return vendingMachineServiceImpl.insertCoin(coin);
    }

    @GetMapping("/reset")
    public List<Coin> reset() {
        return vendingMachineServiceImpl.reset();
    }

    @PostMapping("/buy")
    public OrderProduct buyProduct(@RequestBody String orderType){
        return vendingMachineServiceImpl.buyProduct(orderType);
    }
}
