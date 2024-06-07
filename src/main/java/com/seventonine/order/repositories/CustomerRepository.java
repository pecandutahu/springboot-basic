package com.seventonine.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seventonine.order.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    
}
