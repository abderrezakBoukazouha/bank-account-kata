package org.example.services;

import org.example.dto.CustomerDto;
import org.example.entities.Account;
import org.example.entities.Customer;
import org.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public ResponseEntity<Customer> add(CustomerDto customerDto) {

        Customer customer = Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .dateOfBirth(customerDto.getDateOfBirth())
                .build();

        Account account = Account.builder()
                .amount(0)
                .customer(customer)
                .transas(List.of())
                .build();

        customer.setAccount(account);

        Customer Customer = customerRepository.save(customer);

        return new ResponseEntity<Customer> (customer,HttpStatus.CREATED);
    }

    public Customer findBankUserById (long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
