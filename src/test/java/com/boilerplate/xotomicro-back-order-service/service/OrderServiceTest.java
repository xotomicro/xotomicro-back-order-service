package com.boilerplate.xotomicro_back_order_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.boilerplate.xotomicro_back_order_service.model.Order;
import com.boilerplate.xotomicro_back_order_service.repository.OrderRepository;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        List<Order> orderList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Order order = new Order();
            order.setId((long) i);
            order.setOrderType("OrderType " + i);
            order.setProductDescription("Product Description " + i);
            order.setProductName("Product " + i);
            orderList.add(order);
        }
        when(orderRepository.findAll()).thenReturn(orderList);
        when(orderRepository.findByProductName("Product 1")).thenReturn(orderList.get(0));
        when(orderRepository.save(orderList.get(0))).thenReturn(orderList.get(0));
    }

    @DisplayName("Get All Order")
    @Test
    void getAll() {
        assertEquals(5, orderService.getAll().size());
        assertNotNull(orderService.getAll());
    }

    @DisplayName("Save Order")
    @Test
    public void save() {
        Order order = orderRepository.findAll().get(0);
        orderService.save(order);
    }

    @DisplayName("Get Order By ProductName")
    @Test
    public void getByProductName() {
        Order result = orderService.getByProductName("Product 1");
        Order result2 = orderService.getByProductName("Product 2");
        assertNotNull(result);
        assertNull(result2);
    }
}
