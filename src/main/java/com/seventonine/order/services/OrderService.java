package com.seventonine.order.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventonine.order.models.OrderItem;
import com.seventonine.order.models.Orders;
import com.seventonine.order.repositories.OrdersRepository;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    public Orders saveOrders(Orders order){
        Orders savedOrder = orderRepository.save(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrderId(savedOrder.getOrderId());
            orderItemService.savOrderItem(orderItem);
        }
        return orderRepository.save(order);
    }

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Orders updatOrders(Integer id, Orders orderDetails) {
        Orders order = orderRepository.findById(id).get();
        order.setOrderCode(orderDetails.getOrderCode());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setCustomer(orderDetails.getCustomer());
        order.setOrderItems(orderDetails.getOrderItems());
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
