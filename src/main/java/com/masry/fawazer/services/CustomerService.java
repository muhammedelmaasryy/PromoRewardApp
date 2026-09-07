package com.masry.fawazer.services;

import com.masry.fawazer.dtos.CustomerDTO;
import com.masry.fawazer.exceptions.CustomerNotFoundException;
import com.masry.fawazer.exceptions.SegmentNotFoundException;
import com.masry.fawazer.models.Customer;
import com.masry.fawazer.models.Segment;
import com.masry.fawazer.repositories.CustomerRepository;
import com.masry.fawazer.repositories.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final SegmentRepository segmentRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, SegmentRepository segmentRepository) {
        this.customerRepository = customerRepository;
        this.segmentRepository = segmentRepository;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Segment segment = segmentRepository.findById(customerDTO.getSegmentId())
                .orElseThrow(() -> new SegmentNotFoundException("Segment with ID " + customerDTO.getSegmentId() + " not found"));

        Customer customer = new Customer(customerDTO.getPhoneNumber(), customerDTO.getName(), segment);
        Customer savedCustomer = customerRepository.save(customer);
        
        return new CustomerDTO(savedCustomer.getPhoneNumber(), savedCustomer.getName(), savedCustomer.getSegment().getSegmentId());
    }

    public CustomerDTO getCustomer(String phoneNumber) {
        Customer customer = getCustomerEntity(phoneNumber);
        return new CustomerDTO(customer.getPhoneNumber(), customer.getName(), customer.getSegment().getSegmentId());
    }

    // For internal service-to-service use — returns the entity, not the DTO
    public Customer getCustomerEntity(String phoneNumber) {
        return customerRepository.findById(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with phone number " + phoneNumber + " not found"));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerDTO(customer.getPhoneNumber(), customer.getName(), customer.getSegment().getSegmentId()))
                .toList();
    }

    public CustomerDTO updateCustomer(String phoneNumber, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with phone number " + phoneNumber + " not found"));

        Segment segment = segmentRepository.findById(customerDTO.getSegmentId())
                .orElseThrow(() -> new SegmentNotFoundException("Segment with ID " + customerDTO.getSegmentId() + " not found"));

        customer.setName(customerDTO.getName());
        customer.setSegment(segment);
        Customer updatedCustomer = customerRepository.save(customer);

        return new CustomerDTO(updatedCustomer.getPhoneNumber(), updatedCustomer.getName(), updatedCustomer.getSegment().getSegmentId());
    }

    public void deleteCustomer(String phoneNumber) {
        if (!customerRepository.existsById(phoneNumber)) {
            throw new CustomerNotFoundException("Customer with phone number " + phoneNumber + " not found");
        }
        customerRepository.deleteById(phoneNumber);
    }
}
