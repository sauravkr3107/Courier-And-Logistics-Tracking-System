package org.courier.logisticstrackingsystem.service;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.DeliveryAgent;
import org.springframework.http.ResponseEntity;

public interface DeliveryAgentService {
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> saveDeliveryAgent(DeliveryAgent deliveryAgent);
	
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllDeliveryAgent();
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentById(Integer deliveryAgentId);
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByVehicleNumber(String vehicleNumber);
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByContact(String deliveryAgentPhone);
	
}
