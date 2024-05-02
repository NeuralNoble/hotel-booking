package org.group18.hotelbooking.repository;



import org.group18.hotelbooking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
