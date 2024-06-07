package com.seventonine.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seventonine.order.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    @Query("SELECT c.customerCode FROM Customer c ORDER BY c.customerCode DESC LIMIT 1")
    String findMaxCustomerCode();
}
