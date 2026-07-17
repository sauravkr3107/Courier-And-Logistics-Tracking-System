package org.courier.logisticstrackingsystem.controller;

import java.util.List;
import java.util.Map;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.DeliveryAgent;
import org.courier.logisticstrackingsystem.service.DeliveryAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveryAgent")
public class DeliveryAgentController {
	
	@Autowired
	private DeliveryAgentService deliveryAgentService;
	
	@PostMapping
    public ResponseEntity<ResponseStructure<DeliveryAgent>> saveDeliveryAgent(@RequestBody DeliveryAgent deliveryAgent) {
    	return deliveryAgentService.saveDeliveryAgent(deliveryAgent);
    }
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllDeliveryAgent() {
		return deliveryAgentService.getAllDeliveryAgent();
	}
	
	@GetMapping("/deliveryAgentId/{deliveryAgentId}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentById(@PathVariable Integer deliveryAgentId) {
		return deliveryAgentService.getDeliveryAgentById(deliveryAgentId);
	}
	
	@GetMapping("/vehicleNumber/{vehicleNumber}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByVehicleNumber(@PathVariable String vehicleNumber) {
		return deliveryAgentService.getDeliveryAgentByVehicleNumber(vehicleNumber);
	}
	
	@GetMapping("/deliveryContact/{deliveryAgentPhone}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByContact(@PathVariable String deliveryAgentPhone) {
		return deliveryAgentService.getDeliveryAgentByContact(deliveryAgentPhone);
	}
	
	@GetMapping("/rating/{rating}")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getDeliveryAgentByRatingGreaterThanValue(@PathVariable Integer rating) {
		return deliveryAgentService.getDeliveryAgentByRatingGreaterThanValue(rating);
	}
	
	@PatchMapping("/deliveryAgentId/{deliveryAgentId}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgent(@PathVariable Integer deliveryAgentId, @RequestBody Map<String, Object> map) {
		return deliveryAgentService.updateDeliveryAgent(deliveryAgentId, map);
	}
	
	@DeleteMapping("/deliveryAgentId/{deliveryAgentId}")
	public ResponseEntity<ResponseStructure<String>> deleteDeliveryAgent(@PathVariable Integer deliveryAgentId) {
		return deliveryAgentService.deleteDeliveryAgent(deliveryAgentId);
	}
	
	@PatchMapping("/deliveryAgentId/{deliveryAgentId}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgentAvailablity(@PathVariable Integer deliveryAgentId) {
		return deliveryAgentService.updateDeliveryAgentAvailablity(deliveryAgentId);
	}
	
}
