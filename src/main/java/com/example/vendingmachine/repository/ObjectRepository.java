package com.example.vendingmachine.repository;

import java.util.List;

public interface ObjectRepository<T>  {
     T add(T object);
     T update(T object);
     T remove(Long id);
     List<T> retrieveAll();
}
