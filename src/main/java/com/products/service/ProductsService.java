package com.products.service;

import com.products.model.Products;
import com.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(int id) {
        return productsRepository.findById(id).orElse(null);
    }

    public Products saveProduct(Products product) {
        return productsRepository.save(product);
    }

    public void deleteProduct(int id) {
        productsRepository.deleteById(id);
    }
}
