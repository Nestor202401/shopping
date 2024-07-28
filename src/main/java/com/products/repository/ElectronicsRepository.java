package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.model.Electronics;

public interface ElectronicsRepository extends JpaRepository<Electronics, Integer> {

}
