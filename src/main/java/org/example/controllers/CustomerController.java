package org.example.controllers;

import org.example.dto.CustomerDto;
import org.example.entities.Customer;
import org.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("api/v1/customer")
    public ResponseEntity<Customer> addBankUser(@RequestBody CustomerDto customerDto) {
        return customerService.add(customerDto);
    }
}
