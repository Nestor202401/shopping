package com.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.model.OrderDetail;
import com.users.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail getOrderDetailById(Integer id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail updateOrderDetail(Integer id, OrderDetail orderDetailDetails) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(null);
        if (orderDetail != null) {
            orderDetail.setQuantity(orderDetailDetails.getQuantity());
            orderDetail.setPrice(orderDetailDetails.getPrice());
            return orderDetailRepository.save(orderDetail);
        }
        return null;
    }

    public void deleteOrderDetail(Integer id) {
        orderDetailRepository.deleteById(id);
    }
}
