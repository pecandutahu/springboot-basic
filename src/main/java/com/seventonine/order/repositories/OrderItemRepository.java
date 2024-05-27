package com.seventonine.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seventonine.order.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    
}
