package com.example.vendingmachine.repository;

import com.example.vendingmachine.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InventoryRepository implements ObjectRepository<Product> {

    private Map<Long, Product> repository;

    public InventoryRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public Product add(Product object) {
        repository.put(object.getId(), object);
        return repository.get(object.getId()); //Optional.of(repository.get(object.getId()));
    }

    public Map<Long, Product> getRepository() {
        return repository;
    }

    public void setRepository(Map<Long, Product> repository) {
        this.repository = repository;
    }

    @Override
    public Product update(Product object) {
        for (Map.Entry<Long, Product> entry : repository.entrySet()) {
            if (entry.getKey().equals(object.getId())) {
                this.repository.put(object.getId(), object);
                break;
            }
        }
        return repository.get(object.getId());
    }

    @Override
    public Product remove(Long id) {
        Product removedProduct = repository.get(id);
        this.repository.remove(id);
        return removedProduct;
    }

    @Override
    public List<Product> retrieveAll() {
        List<Product> list = new ArrayList<>(this.repository.values());
        return list;
    }
}
