package com.example.modulzaro4_orders.controller;

import com.example.modulzaro4_orders.model.Person;
import com.example.modulzaro4_orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private OrderService orderService;

    @GetMapping()
    public String showPerson(Model model) {
        List<Person> persons = orderService.getAllPerson();
        model.addAttribute("persons", persons);
        return "person";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", orderService.getPersonById(id));
        return "show-person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("post_url", "/new");
        return "person-form";
    }

    @PostMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        orderService.saveOrEditPerson(person);
        return "redirect:/person";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("person", orderService.getPersonById(id));
        model.addAttribute("post_url", "/edit/" + id);
        return "person-form";
    }

    @PostMapping("/edit/{id}")
    public String editPerson(@ModelAttribute("person") Person person) {
        orderService.saveOrEditPerson(person);
        return "redirect:/person";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Integer id) {
        orderService.deleteExpensesByPerson(orderService.getPersonById(id));
        orderService.deletePersonById(id);
        return "redirect:/person";
    }

    @GetMapping("/most-expense")
    public String getMostExpense(Model model) {
        Person person = orderService.getMostExpensePerson();
        model.addAttribute("person", person);
        model.addAttribute("order", orderService.getAllOrdersByPerson(person));
        return "show-person";
    }
}
