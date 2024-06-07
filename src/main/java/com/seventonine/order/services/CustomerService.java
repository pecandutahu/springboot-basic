package com.seventonine.order.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.stereotype.Service;

import com.seventonine.order.exceptions.ResourceNotFoundException;
import com.seventonine.order.models.Customer;
import com.seventonine.order.repositories.CustomerRepository;


@Service

public class CustomerService {
    
    @Autowired
    // private Validator validator;
    
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customers) {

        String newCode = generateCustomerCode();
        customers.setCustomerCode(newCode);
        return customerRepository.save(customers);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return customer;
    }

    public Customer updateCustomer(Integer customerId, Customer customersDetails) {
        Customer customers = customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer Not Found"));
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
    private String generateCustomerCode() {
        String maxCode = customerRepository.findMaxCustomerCode();
        String newCode = null;

        if (maxCode != null && !maxCode.isEmpty()) {
            String datePart = maxCode.substring(5, 13);
            String sequencePart = maxCode.substring(14);

            int sequenceNumber = Integer.parseInt(sequencePart) + 1;
            newCode = String.format("CUST-%s-%04d", datePart, sequenceNumber);
        } else {
            String datePattern = "yyyyMMdd";
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            String date = sdf.format(new Date());
            newCode = String.format("CUST-%s-%04d", date, 1);
        }

        return newCode;
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
