package com.example.modulzaro4_orders.controller;


import com.example.modulzaro4_orders.model.Orders;
import com.example.modulzaro4_orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @GetMapping()
    public String showOrder(Model model) {
        List<Orders> orders = orderService.getAllOrder();
        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "show-order";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new Orders());
        model.addAttribute("post_url", "/new");
        return "order-form";
    }

    @PostMapping("/new")
    public String newOrder(@ModelAttribute("order") Orders orders) {
        orderService.saveOrEditOrder(orders);
        return "redirect:/order";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("post_url", "/edit/" + id);
        return "order-form";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@ModelAttribute("order") Orders orders) {
        orderService.saveOrEditOrder(orders);
        return "redirect:/order";
    }
}
