package com.seventonine.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seventonine.order.models.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer>{
    
}
