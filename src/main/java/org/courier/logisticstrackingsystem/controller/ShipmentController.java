package org.courier.logisticstrackingsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Shipment;
import org.courier.logisticstrackingsystem.entity.ShipmentStatus;
import org.courier.logisticstrackingsystem.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

	@Autowired
	private ShipmentService shipmentService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Shipment>> saveShipment(@RequestBody Shipment shipment) {
		return shipmentService.saveShipment(shipment);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Shipment>>> getAllShipment() {
		return shipmentService.getAllShipment();
	}

	@GetMapping("/shipmentId/{shipmentId}")
	public ResponseEntity<ResponseStructure<Shipment>> getShipmentByShipmentId(@PathVariable Integer shipmentId) {
		return shipmentService.getShipmentByShipmentId(shipmentId);
	}

	@GetMapping("/trackingNumber/{trackingNumber}")
	public ResponseEntity<ResponseStructure<Shipment>> getShipmentByTrackingNumber(
			@PathVariable String trackingNumber) {
		return shipmentService.getShipmentByTrackingNumber(trackingNumber);
	}

	@PutMapping("/shipmentId/{shipmentId}/shipmentStatus/{shipmentStatus}")
	public ResponseEntity<ResponseStructure<Shipment>> updateShipmentByStatus(@PathVariable Integer shipmentId,
			@PathVariable ShipmentStatus shipmentStatus) {
		return shipmentService.updateShipmentByStatus(shipmentId, shipmentStatus);
	}

	@PostMapping("/shipmentId/{shipmentId}/deliveryAgentId/{deliveryAgentId}")
	public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(@PathVariable Integer shipmentId,
			@PathVariable Integer deliveryAgentId) {
		return shipmentService.assignDeliveryAgent(shipmentId, deliveryAgentId);
	}

	@PostMapping("/shipmentId/{shipmentId}/wareHouseId/{wareHouseId}")
	public ResponseEntity<ResponseStructure<Shipment>> assignWareHouse(@PathVariable Integer shipmentId,
			@PathVariable Integer wareHouseId) {
		return shipmentService.assignWareHouse(shipmentId, wareHouseId);
	}

	@DeleteMapping("/shipmentId/{shipmentId}")
	public ResponseEntity<ResponseStructure<String>> deleteShipment(@PathVariable Integer shipmentId) {
		return shipmentService.deleteShipment(shipmentId);
	}

	@GetMapping("/customerId/{customerId}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByCustomerId(@PathVariable Integer customerId) {
		return shipmentService.getShipmentByCustomerId(customerId);
	}

	@GetMapping("/wareHouseId/{wareHouseId}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByWareHouseId(
			@PathVariable Integer wareHouseId) {
		return shipmentService.getShipmentByWareHouseId(wareHouseId);
	}

	@GetMapping("/deliveryAgentId/{deliveryAgentId}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByDeliveryAgent(
			@PathVariable Integer deliveryAgentId) {
		return shipmentService.getShipmentByDeliveryAgent(deliveryAgentId);
	}

	@GetMapping("/source/{source}/destination/{destination}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentBySourceAndDestination(
			@PathVariable String source, @PathVariable String destination) {
		return shipmentService.getShipmentBySourceAndDestination(source, destination);
	}

	@GetMapping("/deliveryDate")
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByDeliveryDate(
			@RequestParam LocalDate deliveryDate) {
		return shipmentService.getShipmentByDeliveryDate(deliveryDate);
	}

	@GetMapping("/pageNumber/{pageNumber}/pageSize/{pageSize}/fieldName/{fieldName}")
	public ResponseEntity<ResponseStructure<Page<Shipment>>> getShipmentByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String fieldName) {
		return getShipmentByPaginationAndSorting(pageNumber, pageSize, fieldName);
	}
}
