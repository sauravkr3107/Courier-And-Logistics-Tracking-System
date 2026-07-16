package org.courier.logisticstrackingsystem.repository;

import org.courier.logisticstrackingsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
