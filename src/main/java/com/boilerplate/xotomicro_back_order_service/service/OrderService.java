package com.boilerplate.xotomicro_back_order_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.boilerplate.xotomicro_back_order_service.model.Order;
import com.boilerplate.xotomicro_back_order_service.repository.OrderRepository;

@Component
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    @Cacheable(value = "order", unless = "#result == null")
    public Order getByProductName(String productName) {
        logger.info("Get order by product name: {}", productName);
        return orderRepository.findByProductName(productName);
    }
}
