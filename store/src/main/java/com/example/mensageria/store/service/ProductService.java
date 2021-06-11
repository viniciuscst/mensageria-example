package com.example.mensageria.store.service;

import com.example.mensageria.store.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();
    public Optional<Product> findById(Long id);
    public Product save(Product product);
    public Product addInvoice(Long id);
}
