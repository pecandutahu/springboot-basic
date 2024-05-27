package com.seventonine.order.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventonine.order.models.OrderItem;
import com.seventonine.order.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    @Autowired

    private OrderItemRepository orderItemRepository;
   
    public List<OrderItem> getAllOrderItems(){
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Integer id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem savOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Integer Id) {
        orderItemRepository.deleteById(Id);
    }
    
}
