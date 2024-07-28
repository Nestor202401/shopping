package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.products.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
}