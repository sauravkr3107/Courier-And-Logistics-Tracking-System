package org.courier.logisticstrackingsystem.repository;

import org.courier.logisticstrackingsystem.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

}
