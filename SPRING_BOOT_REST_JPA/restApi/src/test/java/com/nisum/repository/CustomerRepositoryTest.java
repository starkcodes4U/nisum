package com.nisum.repository;

import com.nisum.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByEmailContaining() {
        Customer c = new Customer("John", "john@example.com", LocalDate.now());
        customerRepository.save(c);
        List<Customer> result = customerRepository.findByEmailContaining("example");
        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindByRegisteredDateAfter() {
        Customer c = new Customer("Jane", "jane@example.com", LocalDate.now().minusDays(5));
        customerRepository.save(c);
        List<Customer> result = customerRepository.findByRegisteredDateAfter(LocalDate.now().minusDays(10));
        assertThat(result).isNotEmpty();
    }
}
