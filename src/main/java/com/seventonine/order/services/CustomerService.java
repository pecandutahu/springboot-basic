package com.seventonine.order.services;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.stereotype.Service;

import com.seventonine.order.dto.request.CustomerRequest;
import com.seventonine.order.dto.response.MessageResponse;
import com.seventonine.order.exceptions.ResourceNotFoundException;
import com.seventonine.order.models.Customer;
import com.seventonine.order.repositories.CustomerRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class CustomerService {
    
    @Autowired
    // private Validator validator;
    
    private CustomerRepository customerRepository;

    @Autowired 
    private MessageSource messageSource;

    public Customer saveCustomer(Customer customers) {
        return customerRepository.save(customers);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Integer customerId, Customer customersDetails) {
        Customer customers = customerRepository.findById(customerId).orElseThrow();
        customers.setCustomerName(customersDetails.getCustomerName());
        customers.setCustomerAddress(customersDetails.getCustomerAddress());
        customers.setCustomerCode(customersDetails.getCustomerCode());
        customers.setCustomerPhone(customersDetails.getCustomerPhone());
        customers.setIsActive(customersDetails.getIsActive());
        customers.setLastOrderDate(customersDetails.getLastOrderDate());
        return customerRepository.save(customers);
    }

    public void deleteCustomer(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()) {
            throw new ResourceNotFoundException("Customer not found");
        }
            
        customerRepository.deleteById(id);
    }

    
    // public ResponseEntity<MessageResponse> createCustomer(CustomerRequest request){
    //     try {
            
    //         Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
    //         log.error("request", constraintViolations);
    //         if (constraintViolations.isEmpty()) {
    //             ConstraintViolation<CustomerRequest> firstViolation = constraintViolations.iterator().next();
    //             String message = firstViolation.getMessage();
    //             return ResponseEntity
    //                 .badRequest()
    //                 .body(new MessageResponse(message, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
    //         }

    //         Customers customers = Customers.builder()
    //             .customerName(request.getCustomerName())
    //             .customerAddress(request.getCustomerAddress())
    //             .customerCode(request.getCustomerCode())
    //             .customerPhone(request.getCustomerPhone())
    //             .isActive(request.getIsActive())
    //             .lastOrderDate(request.getLastOrderDate())
    //             .build();
    //         customerRepository.save(customers);

    //         String message = messageSource.getMessage("customer.created", null, Locale.getDefault());
    //         String formatMessage = MessageFormat.format(message, request.getCustomerName());
    //         return ResponseEntity
    //             .ok()
    //             .body(new MessageResponse(formatMessage, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    //     } catch (Exception e) {
    //         log.error(null, e);
    //         String message = messageSource.getMessage("server.error", null, Locale.getDefault());
    //         return ResponseEntity
    //             .internalServerError()
    //             .body(new MessageResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    //     }
    // }
}
