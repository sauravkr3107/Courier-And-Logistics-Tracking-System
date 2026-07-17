package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Map;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.DeliveryAgent;
import org.springframework.http.ResponseEntity;

public interface DeliveryAgentService {
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> saveDeliveryAgent(DeliveryAgent deliveryAgent);
	
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllDeliveryAgent();
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentById(Integer deliveryAgentId);
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByVehicleNumber(String vehicleNumber);
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByContact(String deliveryAgentPhone);
	
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getDeliveryAgentByRatingGreaterThanValue(Integer rating);
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgent(Integer deliveryAgentId, Map<String, Object> map);
	
	public ResponseEntity<ResponseStructure<String>> deleteDeliveryAgent(Integer deliveryAgentId);
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgentAvailablity(Integer deliveryAgentId);
	
}
