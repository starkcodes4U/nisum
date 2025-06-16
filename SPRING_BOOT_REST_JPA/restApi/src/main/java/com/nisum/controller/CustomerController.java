package com.nisum.controller;

import com.nisum.model.Customer;
import com.nisum.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setRegisteredDate(updatedCustomer.getRegisteredDate());
                    return ResponseEntity.ok(customerRepository.save(customer));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/email")
    public List<Customer> findByEmailKeyword(@RequestParam String keyword) {
        return customerRepository.findByEmailContaining(keyword);
    }

    @GetMapping("/search/registered-after")
    public List<Customer> findByRegisteredAfter(@RequestParam String date) {
        return customerRepository.findByRegisteredDateAfter(LocalDate.parse(date));
    }

    @GetMapping("/search/by-name")
    public List<Customer> findByName(@RequestParam String name) {
        return customerRepository.findByName(name);
    }

    @GetMapping("/search/name-contains")
    public List<Customer> findByNameContaining(@RequestParam String namePart) {
        return customerRepository.findByNameContainingIgnoreCase(namePart);
    }

    @GetMapping("/search/email-exact")
    public List<Customer> findByEmail(@RequestParam String email) {
        return customerRepository.findByEmail(email);
    }

    @GetMapping("/search/registered-before")
    public List<Customer> findRegisteredBefore(@RequestParam String date) {
        return customerRepository.findRegisteredBefore(LocalDate.parse(date));
    }

    @GetMapping("/search/email-domain")
    public List<Customer> findByEmailDomain(@RequestParam String domain) {
        return customerRepository.findByEmailDomain(domain);
    }

    @GetMapping("/search/registered-between")
    public List<Customer> findByRegisteredBetween(@RequestParam String start, @RequestParam String end) {
        return customerRepository.findByRegisteredDateBetween(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/count/registered-after")
    public long countByRegisteredAfter(@RequestParam String date) {
        return customerRepository.countByRegisteredDateAfter(LocalDate.parse(date));
    }

    @GetMapping("/exists/email")
    public boolean existsByEmail(@RequestParam String email) {
        return customerRepository.existsByEmail(email);
    }
}
