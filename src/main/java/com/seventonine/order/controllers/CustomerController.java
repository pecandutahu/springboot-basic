package com.seventonine.order.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seventonine.order.dto.response.MessageResponse;
import com.seventonine.order.models.Customers;
import com.seventonine.order.services.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@Slf4j

public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<Customers> createCustomer(@Valid @RequestBody Customers customers) {
        Customers createdCustomer = customerService.saveCustomer(customers);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/lists")
    public List<Customers> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Customers> getDetailCustomer(@PathVariable Integer id) {
        Optional<Customers> customer = customerService.getCustomerById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable Integer id, @Valid @RequestBody Customers customer) {
        Customers updateCustomers = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updateCustomers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
