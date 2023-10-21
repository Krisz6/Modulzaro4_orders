package com.example.modulzaro4_orders.controller;

import com.example.modulzaro4_orders.model.Orders;
import com.example.modulzaro4_orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class Rest {
    private final OrderService orderService;

    @GetMapping("/person/{id}/orders")
    public List<Orders> getAllOrdersByPerson(@PathVariable("id") Integer id) {
        return orderService.getAllOrdersByPerson(orderService.getPersonById(id));
    }
}
