package com.boilerplate.xotomicro_back_order_service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boilerplate.xotomicro_back_order_service.model.Order;
import com.boilerplate.xotomicro_back_order_service.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @ResponseBody
    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }
}
