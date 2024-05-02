package org.group18.hotelbooking.controllers;

import org.group18.hotelbooking.dto.CustomerDTO;
import org.group18.hotelbooking.models.Booking;
import org.group18.hotelbooking.models.Customer;
import org.group18.hotelbooking.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerDTOs = customerService.getAllCustomers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);
        if (optionalCustomer.isPresent()) {
            CustomerDTO customerDTO = convertToDto(optionalCustomer.get());
            return ResponseEntity.ok(customerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);
        CustomerDTO savedCustomerDTO = convertToDto(savedCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO updatedCustomerDTO) {
        Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(updatedCustomerDTO.getName());
            existingCustomer.setEmail(updatedCustomerDTO.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomerDTO.getPhoneNumber());
            existingCustomer.setAddress(updatedCustomerDTO.getAddress());
            Customer updatedCustomer = customerService.saveCustomer(existingCustomer);
            CustomerDTO updatedCustomerResponse = convertToDto(updatedCustomer);
            return ResponseEntity.ok(updatedCustomerResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}/bookings")
    public ResponseEntity<List<Booking>> getCustomerBookingHistory(@PathVariable Long customerId) {
        List<Booking> bookingHistory = customerService.getCustomerBookingHistory(customerId);
        return ResponseEntity.ok(bookingHistory);
    }

    private CustomerDTO convertToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }

}
