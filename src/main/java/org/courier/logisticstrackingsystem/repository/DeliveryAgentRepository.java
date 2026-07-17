package org.courier.logisticstrackingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer>{

	Optional<DeliveryAgent> findByVehicleNumber(String vehicleNumber);

	Optional<DeliveryAgent> findByDeliveryAgentPhone(String deliveryAgentPhone);

	List<DeliveryAgent> findByRatingGreaterThan(Integer rating);

}
