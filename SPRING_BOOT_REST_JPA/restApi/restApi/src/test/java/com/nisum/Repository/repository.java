package com.nisum.Repository;

import com.nisum.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Should save and retrieve a customer by ID")
    void testSaveAndFindById() {
        Customer customer = new Customer("Amit Sharma", "amit.sharma@example.com", LocalDate.of(2025, 1, 5));
        Customer saved = customerRepository.save(customer);

        Optional<Customer> found = customerRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Amit Sharma");
    }

    @Test
    @DisplayName("Should find all customers")
    void testFindAll() {
        customerRepository.save(new Customer("Priya Singh", "priya.singh@example.com", LocalDate.of(2025, 1, 10)));
        customerRepository.save(new Customer("Rahul Verma", "rahul.verma@example.com", LocalDate.of(2025, 2, 15)));

        List<Customer> all = customerRepository.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    @DisplayName("Should delete a customer")
    void testDeleteById() {
        Customer customer = customerRepository.save(new Customer("Sneha Patel", "sneha.patel@example.com", LocalDate.of(2025, 2, 20)));
        Long id = customer.getId();

        customerRepository.deleteById(id);
        Optional<Customer> deleted = customerRepository.findById(id);
        assertThat(deleted).isNotPresent();
    }

    @Test
    @DisplayName("Should find by email containing keyword")
    void testFindByEmailContaining() {
        customerRepository.save(new Customer("Vikram Gupta", "vikram.gupta@gmail.com", LocalDate.of(2025, 3, 1)));
        customerRepository.save(new Customer("Neha Reddy", "neha.reddy@yahoo.com", LocalDate.of(2025, 3, 12)));

        List<Customer> gmailCustomers = customerRepository.findByEmailContaining("gmail");
        assertThat(gmailCustomers).hasSize(1);
        assertThat(gmailCustomers.get(0).getName()).isEqualTo("Vikram Gupta");
    }

    @Test
    @DisplayName("Should find by registered date after")
    void testFindByRegisteredDateAfter() {
        customerRepository.save(new Customer("Rohit Kumar", "rohit.kumar@example.com", LocalDate.of(2025, 3, 20)));
        customerRepository.save(new Customer("Anjali Joshi", "anjali.joshi@example.com", LocalDate.of(2025, 4, 5)));

        List<Customer> recent = customerRepository.findByRegisteredDateAfter(LocalDate.of(2025, 3, 25));
        assertThat(recent).hasSize(1);
        assertThat(recent.get(0).getName()).isEqualTo("Anjali Joshi");
    }

    @Test
    @DisplayName("Should find by name (JPQL query)")
    void testFindByName() {
        customerRepository.save(new Customer("Suresh Nair", "suresh.nair@example.com", LocalDate.of(2025, 4, 12)));
        customerRepository.save(new Customer("Suresh Nair", "suresh2.nair@example.com", LocalDate.of(2025, 5, 1)));

        List<Customer> sureshs = customerRepository.findByName("Suresh Nair");
        assertThat(sureshs).hasSize(2);
    }
}
