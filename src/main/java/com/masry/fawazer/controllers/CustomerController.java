package com.masry.fawazer.controllers;

import com.masry.fawazer.dtos.CustomerDTO;
import com.masry.fawazer.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO created = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(customerService.getCustomer(phoneNumber));
    }

    @PutMapping("/{phoneNumber}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String phoneNumber, @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(phoneNumber, customerDTO));
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String phoneNumber) {
        customerService.deleteCustomer(phoneNumber);
        return ResponseEntity.noContent().build();
    }
}
