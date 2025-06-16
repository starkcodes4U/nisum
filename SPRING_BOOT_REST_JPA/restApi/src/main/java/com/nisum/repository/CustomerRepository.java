package com.nisum.repository;

import com.nisum.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEmailContaining(String keyword);
    List<Customer> findByRegisteredDateAfter(LocalDate date);

    @Query("SELECT c FROM Customer c WHERE c.name = ?1")
    List<Customer> findByName(String name);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Customer> findByNameContainingIgnoreCase(String namePart);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    List<Customer> findByEmail(String email);

    @Query(value = "SELECT * FROM customer_db WHERE registered_date < ?1", nativeQuery = true)
    List<Customer> findRegisteredBefore(LocalDate date);

    @Query(value = "SELECT * FROM customer_db WHERE email LIKE %?1", nativeQuery = true)
    List<Customer> findByEmailDomain(String domain);

    List<Customer> findByRegisteredDateBetween(LocalDate start, LocalDate end);
    long countByRegisteredDateAfter(LocalDate date);
    boolean existsByEmail(String email);
}