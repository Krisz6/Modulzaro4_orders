package com.example.modulzaro4_orders.service;

import com.example.modulzaro4_orders.model.Orders;
import com.example.modulzaro4_orders.model.Person;
import com.example.modulzaro4_orders.repo.OrdersRepo;
import com.example.modulzaro4_orders.repo.PersonRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {
    private OrdersRepo ordersRepo;
    private PersonRepo personRepo;

    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    public List<Orders> getAllOrder() {
        return ordersRepo.findAll();
    }

    public Person getPersonById(Integer id) {
        return personRepo.findById(id).orElse(null);
    }

    public void saveOrEditPerson(Person person) {
        personRepo.save(person);
    }

    public Object getOrderById(Integer id) {
        return ordersRepo.findById(id).orElse(null);
    }

    public void saveOrEditOrder(Orders orders) {
        ordersRepo.save(orders);
    }

    @Transactional
    public void deleteExpensesByPerson(Person person) {
        ordersRepo.deleteAllByOrderedBy(person);
    }

    @Transactional
    public void deletePersonById(Integer id) {
        personRepo.deleteById(id);
    }

    public List<Orders> getAllOrdersByPerson(Person person) {
        return ordersRepo.getExpensesByOrderedBy(person);
    }

    public Person getMostExpensePerson() {
        Map<Person, Integer> personExpenses = fillPersonsMap();
        return getMaxExpensesFromPersonExpensesMap(personExpenses);
    }

    public Person getMaxExpensesFromPersonExpensesMap(Map<Person, Integer> personExpenses) {
        int max = 0;
        for (var actual : personExpenses.entrySet()) {
            if (actual.getValue() > max) {
                max = actual.getValue();
            }
        }
        for (var actual : personExpenses.entrySet()) {
            if (actual.getValue() == max) {
                return actual.getKey();
            }
        }
        return null;
    }

    private Map<Person, Integer> fillPersonsMap() {
        Map<Person, Integer> personExpenses = new HashMap<>();
        fillPersonsMapWithPerson(personExpenses);
        fillPersonsMapWithExpenses(personExpenses);
        return personExpenses;
    }

    private void fillPersonsMapWithPerson(Map<Person, Integer> personExpenses) {
        List<Person> persons = getAllPerson();
        for (Person actual : persons) {
            personExpenses.put(actual, 0);
        }
    }

    private void fillPersonsMapWithExpenses(Map<Person, Integer> personExpenses) {
        List<Orders> expenses = getAllOrder();
        for (Orders actual : expenses) {
            Person actualPerson = actual.getOrderedBy();
            personExpenses.put(actualPerson, personExpenses.get(actualPerson) + actual.getPrice());
        }
    }
}
