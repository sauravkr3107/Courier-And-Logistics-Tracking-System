package org.courier.logisticstrackingsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Customer;
import org.courier.logisticstrackingsystem.entity.DeliveryAgent;
import org.courier.logisticstrackingsystem.entity.Shipment;
import org.courier.logisticstrackingsystem.entity.ShipmentStatus;
import org.courier.logisticstrackingsystem.entity.TrackingHistory;
import org.courier.logisticstrackingsystem.entity.WareHouse;
import org.courier.logisticstrackingsystem.exceptions.CustomerNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.DeliveryAgentNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.exceptions.PackageEntityNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.PaymentNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.TrackingHistoryNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.WareHouseNotFoundException;
import org.courier.logisticstrackingsystem.repository.CustomerRepository;
import org.courier.logisticstrackingsystem.repository.DeliveryAgentRepository;
import org.courier.logisticstrackingsystem.repository.ShipmentRepository;
import org.courier.logisticstrackingsystem.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;
	
	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Override
	public ResponseEntity<ResponseStructure<Shipment>> saveShipment(Shipment shipment) {
		
		if (shipment.getCustomer() == null || shipment.getCustomer().getCustomerId() == null) {
			throw new CustomerNotFoundException("Customer is required");
		}
		
		if (shipment.getWareHouse() == null || shipment.getWareHouse().getWareHouseId() == null) {
			throw new WareHouseNotFoundException("WareHouse is required");
		}
		
		if (shipment.getDeliveryAgent() == null || shipment.getDeliveryAgent().getDeliveryAgentId() == null) {
			throw new DeliveryAgentNotFoundException("Delivery Agent is required");
		}
		
		if (shipment.getPackageEntity() == null) {
			throw new PackageEntityNotFoundException("Package Entity is required");
		}
		
		if (shipment.getPayment() == null) {
			throw new PaymentNotFoundException("Payment is required");
		}
		
		if (shipment.getTrackingHistories() == null || shipment.getTrackingHistories().isEmpty()) {
			throw new TrackingHistoryNotFoundException("Tracking History is required");
		}
		
		if (shipmentRepository.existsByTrackingNumber(shipment.getTrackingNumber())) {
			throw new RuntimeException("Tracking Number already exists");
		}
		
		Optional<DeliveryAgent> agent = deliveryAgentRepository.findById(shipment.getDeliveryAgent().getDeliveryAgentId());
		if (!agent.isPresent()) {
			throw new RuntimeException("Delivery Agent not found");
		}
		
		if (!agent.get().getAvailablityStatus()) {
			throw new RuntimeException("Delivery Agent is unavailable");
		}
		
		agent.get().setAvailablityStatus(false);
		
		double totalPrice = calculatePrice(shipment.getWeight(), shipment.getPackageEntity().getFragile());
		
		shipment.getPayment().setAmount(totalPrice);
		
		Optional<Customer> customer = customerRepository.findById(shipment.getCustomer().getCustomerId());
		
		if (!customer.isPresent()) {
			throw new RuntimeException("Customer noty found");
		}
		
		Optional<WareHouse> wareHouse = wareHouseRepository.findById(shipment.getWareHouse().getWareHouseId());
		
		if (!wareHouse.isPresent()) {
			throw new RuntimeException("WareHouse not found");
		}
		
		shipment.setCustomer(customer.get());
		shipment.setWareHouse(wareHouse.get());
		shipment.setDeliveryAgent(agent.get());
		
		shipment.getPayment().setShipment(shipment);
		shipment.getPackageEntity().setShipment(shipment);
		
		for (TrackingHistory history : shipment.getTrackingHistories()) {
			history.setShipment(shipment);
		}
		
		Shipment savedShipment = shipmentRepository.save(shipment);
		
		ResponseStructure<Shipment> response = new ResponseStructure<Shipment>();
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Shipment created successfully");
		response.setData(savedShipment);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private double calculatePrice(Double weight, Boolean fragile) {
		double price;
		
		if (weight <= 1) {
			price = 100;
		}
		else if (weight <= 5) {
			price = 250;
		}
		else if (weight <= 10) {
			price = 500;
		}
		else {
			price = 800 + ((weight - 10) * 40);
		}
		
		if (fragile == true) {
			price += 150;
		}
		
		return price;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Shipment>>> getAllShipment() {
		
		List<Shipment> shipments = shipmentRepository.findAll();
		
		ResponseStructure<List<Shipment>> response = new ResponseStructure<List<Shipment>>();
		
		if (!shipments.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All shipments record retrieved successfully");
			response.setData(shipments);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Shipment>> getShipmentByShipmentId(Integer shipmentId) {
		
		Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);
		
		ResponseStructure<Shipment> response = new ResponseStructure<Shipment>();
		
		if (shipment.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Shipment>> getShipmentByTrackingNumber(String trackingNumber) {
		
		Optional<Shipment> shipment = shipmentRepository.findByTrackingNumber(trackingNumber);
		
		ResponseStructure<Shipment> response = new ResponseStructure<Shipment>();
		
		if (shipment.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("Tracking number is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Shipment>> updateShipmentByStatus(Integer shipmentId, ShipmentStatus shipmentStatus) {
		
		Optional<Shipment> opt = shipmentRepository.findById(shipmentId);
		
		ResponseStructure<Shipment> response = new ResponseStructure<Shipment>();
		
		if (opt.isPresent()) {
			Shipment shipment = opt.get();
			shipment.setShipmentStatus(shipmentStatus);
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment status updated successfully");
			response.setData(shipmentRepository.save(shipment));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(Integer shipmentId, Integer deliveryAgentId) {
		
		Optional<DeliveryAgent> optAgent = deliveryAgentRepository.findById(deliveryAgentId);
		if (!optAgent.isPresent()) {
			throw new IdNotFoundException("Agent Id is not found");
		}
		
	    Optional<Shipment> optShipment = shipmentRepository.findById(shipmentId);
	    
		ResponseStructure<Shipment> response = new ResponseStructure<Shipment>();
		
		if (optShipment.isPresent()) {
			Shipment shipment = optShipment.get();
			
			shipment.setDeliveryAgent(optAgent.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Assigned Delivery Agent successfully");
			response.setData(shipmentRepository.save(shipment));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Shipment Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Shipment>> assignWareHouse(Integer shipmentId, Integer wareHouseId) {
		
		Optional<WareHouse> optWareHouse = wareHouseRepository.findById(wareHouseId);
		if (!optWareHouse.isPresent()) {
			throw new IdNotFoundException("WareHouse Id is not found");
		}
		
	    Optional<Shipment> optShipment = shipmentRepository.findById(shipmentId);
	    
		ResponseStructure<Shipment> response = new ResponseStructure<Shipment>();
		
		if (optShipment.isPresent()) {
			Shipment shipment = optShipment.get();
			
			shipment.setWareHouse(optWareHouse.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Assigned Delivery Agent successfully");
			response.setData(shipmentRepository.save(shipment));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Shipment Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteShipment(Integer shipmentId) {
		
		Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);
		
		ResponseStructure<String> response = new ResponseStructure<String>();
		
		if (shipment.isPresent()) {
			shipmentRepository.delete(shipment.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment deleted successfully");
			response.setData("Success");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByCustomerId(Integer customerId) {
		
		List<Shipment> shipment = shipmentRepository.findByCustomerCustomerId(customerId);
		
		ResponseStructure<List<Shipment>> response = new ResponseStructure<List<Shipment>>();
		
		if (!shipment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Customer Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByWareHouseId(Integer wareHouseId) {
		
		List<Shipment> shipment = shipmentRepository.findByWareHouseWareHouseId(wareHouseId);
		
		ResponseStructure<List<Shipment>> response = new ResponseStructure<List<Shipment>>();
		
		if (!shipment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("WareHouse Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByDeliveryAgent(Integer deliveryAgentId) {
		
		List<Shipment> shipment = shipmentRepository.findByDeliveryAgentDeliveryAgentId(deliveryAgentId);
		
		ResponseStructure<List<Shipment>> response = new ResponseStructure<List<Shipment>>();
		
		if (!shipment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("DeliveryAgent Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentBySourceAndDestination(String source,
			String destination) {
		
		List<Shipment> shipment = shipmentRepository.findBySourceAndDestination(source, destination);
		
		ResponseStructure<List<Shipment>> response = new ResponseStructure<List<Shipment>>();
		
		if (!shipment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Shipment>>> getShipmentByDeliveryDate(LocalDate deliveryDate) {
		
		List<Shipment> shipment = shipmentRepository.findByDeliveryDate(deliveryDate);
		
		ResponseStructure<List<Shipment>> response = new ResponseStructure<List<Shipment>>();
		
		if (!shipment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Shipment retrieved successfully");
			response.setData(shipment);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Page<Shipment>>> getShipmentByPaginationAndSorting(Integer pageNumber,
			Integer pageSize, String fieldName) {
		
		Page<Shipment> page = shipmentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(fieldName).descending()));
		
		ResponseStructure<Page<Shipment>> response = new ResponseStructure<Page<Shipment>>();
		
		if (!page.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("SHipment retrieved successfully");
			response.setData(page);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("Data not available to be displayed");
		}
	}
	
}
