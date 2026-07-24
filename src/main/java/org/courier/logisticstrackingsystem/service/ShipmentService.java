package org.courier.logisticstrackingsystem.service;

import java.time.LocalDate;
import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Shipment;
import org.courier.logisticstrackingsystem.entity.ShipmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ShipmentService {
	
	public ResponseEntity<ResponseStructure<Shipment>> saveShipment(Shipment shipment);
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> getAllShipment();
	
	public ResponseEntity<ResponseStructure<Shipment>> getShipmentByShipmentId(Integer shipmentId);
	
	public ResponseEntity<ResponseStructure<Shipment>> getShipmentByTrackingNumber(String trackingNumber);
	
	public ResponseEntity<ResponseStructure<Shipment>> updateShipmentByStatus(Integer shipmentId, ShipmentStatus shipmentStatus);
	
	public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(Integer shipmentId, Integer deliveryAgentId);
	
	public ResponseEntity<ResponseStructure<Shipment>> assignWareHouse(Integer shipmentId, Integer wareHouseId);
	
	public ResponseEntity<ResponseStructure<String>> deleteShipment(Integer shipmentId);
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByCustomerId(Integer customerId);
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByWareHouseId(Integer wareHouseId);
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByDeliveryAgent(Integer deliveryAgentId);
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentBySourceAndDestination(String source, String destination);
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByDeliveryDate(LocalDate deliveryDate);
	
	public ResponseEntity<ResponseStructure<Page<Shipment>>> getShipmentByPaginationAndSorting(Integer pageNumber, Integer pageSize, String fieldName);
	
}
