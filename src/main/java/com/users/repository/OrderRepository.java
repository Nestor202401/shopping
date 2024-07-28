package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
