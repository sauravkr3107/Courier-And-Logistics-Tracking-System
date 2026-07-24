package org.courier.logisticstrackingsystem.repository;

import java.util.Optional;

import org.courier.logisticstrackingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findCustomerByEmail(String email);

	Optional<Customer> findByCustomerPhone(String customerPhone);

	boolean existsByEmail(String email);

	boolean existsByCustomerPhone(String customerPhone);

}
