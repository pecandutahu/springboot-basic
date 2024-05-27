package com.seventonine.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seventonine.order.models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
    
}
