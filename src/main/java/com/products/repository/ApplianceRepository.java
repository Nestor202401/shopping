package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.products.model.Appliance;

public interface ApplianceRepository extends JpaRepository<Appliance, Integer> {
}