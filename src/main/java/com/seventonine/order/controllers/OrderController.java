package com.seventonine.order.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seventonine.order.models.Orders;
import com.seventonine.order.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired

    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        return ResponseEntity.ok(orderService.saveOrders(orders));
    }

    @GetMapping("/lists")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/lists/{id}")
    public ResponseEntity<Orders> getDetailOrder(@PathVariable Integer id) {
        Optional<Orders> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer id, @RequestBody Orders order) {
        Orders updatedOrder = orderService.updatOrders(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
}
