package com.example.modulzaro4_orders.repo;

import com.example.modulzaro4_orders.model.Orders;
import com.example.modulzaro4_orders.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    Integer deleteAllByOrderedBy(Person person);

    List<Orders> getExpensesByOrderedBy(Person person);

}
