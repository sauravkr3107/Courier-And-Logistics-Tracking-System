package org.courier.logisticstrackingsystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

	Optional<Shipment> findByTrackingNumber(String trackingNumber);

	List<Shipment> findByCustomerCustomerId(Integer customerId);

	List<Shipment> findByWareHouseWareHouseId(Integer wareHouseId);

	List<Shipment> findByDeliveryAgentDeliveryAgentId(Integer deliveryAgentId);

	List<Shipment> findBySourceAndDestination(String source, String destination);

	List<Shipment> findByDeliveryDate(LocalDate deliveryDate);

	boolean existsByCustomerCustomerId(Integer customerId);

	boolean existsByWareHouseWareHouseId(Integer wareHouseId);

	boolean existsByDeliveryAgentDeliveryAgentId(Integer deliveryAgentId);

	boolean existsByTrackingNumber(String trackingNumber);

}
