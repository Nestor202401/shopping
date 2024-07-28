package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {}
