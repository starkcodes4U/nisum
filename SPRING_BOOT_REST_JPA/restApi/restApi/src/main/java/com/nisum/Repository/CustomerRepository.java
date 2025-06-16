package com.nisum.Repository;

import com.nisum.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 1. Find customers whose email contains a keyword (Spring Data derived query)
    List<Customer> findByEmailContaining(String keyword);

    // 2. Find customers registered after a certain date (Spring Data derived query)
    List<Customer> findByRegisteredDateAfter(LocalDate date);

    // 3. Find customers by exact name using JPQL
    @Query("SELECT c FROM customer_db c WHERE c.name = ?1")
    List<Customer> findByName(String name);

    // 4. Find customers by partial name match using JPQL (case-insensitive)
    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Customer> findByNameContainingIgnoreCase(String namePart);

    // 5. Find customers by exact email using JPQL
    @Query("SELECT c FROM customer_db c WHERE c.email = ?1")
    List<Customer> findByEmail(String email);

    // 6. Native query: Find customers registered before a certain date
    @Query(value = "SELECT * FROM customer_db WHERE registered_date < ?1", nativeQuery = true)
    List<Customer> findRegisteredBefore(LocalDate date);

    // 7. Native query: Find customers with a specific domain in email
    @Query(value = "SELECT * FROM customer_db WHERE email LIKE %?1", nativeQuery = true)
    List<Customer> findByEmailDomain(String domain);

    // 8. Find all customers registered between two dates
    List<Customer> findByRegisteredDateBetween(LocalDate start, LocalDate end);

    // 9. Count customers registered after a certain date
    long countByRegisteredDateAfter(LocalDate date);

    // 10. Check if a customer exists by email
    boolean existsByEmail(String email);
}
