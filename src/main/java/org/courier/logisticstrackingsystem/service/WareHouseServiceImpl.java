package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.WareHouse;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoLocationFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.repository.ShipmentRepository;
import org.courier.logisticstrackingsystem.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WareHouseServiceImpl implements WareHouseService {
	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;

	@Override
	public ResponseEntity<ResponseStructure<WareHouse>> saveWareHouse(WareHouse wareHouse) {
		
		if (wareHouseRepository.existsByContactNumber(wareHouse.getContactNumber())) {
			throw new RuntimeException("Contact Number already exists");
		}
		
		if (!wareHouse.getContactNumber().matches("\\d{10}")) {
			throw new RuntimeException("Contact Number must be exactly 10 digits");
		}
		
		WareHouse SavedWareHouse = wareHouseRepository.save(wareHouse);
		
		ResponseStructure<WareHouse> response = new ResponseStructure<WareHouse>();
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("WareHouse created successfully");
		response.setData(SavedWareHouse);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getAllWareHouse() {
		
		List<WareHouse> wareHouse = wareHouseRepository.findAll();
		
		ResponseStructure<List<WareHouse>> response = new ResponseStructure<List<WareHouse>>();
		
		if (!wareHouse.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse retrieved successfully");
			response.setData(wareHouse);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouse>> getWareHouseByWareHouseId(Integer wareHouseId) {
		
		Optional<WareHouse> wareHouse = wareHouseRepository.findById(wareHouseId);
		
		ResponseStructure<WareHouse> response = new ResponseStructure<WareHouse>();
		
		if (wareHouse.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse Retrieved successfully");
			response.setData(wareHouse.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByLocation(String location) {
		
		List<WareHouse> wareHouses = wareHouseRepository.findByLocation(location);
		
		ResponseStructure<List<WareHouse>> response = new ResponseStructure<List<WareHouse>>();
		
		if (!wareHouses.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse retrieved successfully");
			response.setData(wareHouses);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoLocationFoundException("No location is found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByCapacity(Integer capacity) {
		
		List<WareHouse> wareHouses = wareHouseRepository.findByCapacity(capacity);
		
		ResponseStructure<List<WareHouse>> response = new ResponseStructure<List<WareHouse>>();
		
		if (!wareHouses.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse retrieved successfully");
			response.setData(wareHouses);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByCapacityGreaterThan(Integer value) {
		
		List<WareHouse> wareHouses = wareHouseRepository.findByCapacityGreaterThan(value);
		
		ResponseStructure<List<WareHouse>> response = new ResponseStructure<List<WareHouse>>();
		
		if (!wareHouses.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse Retrieved successfully");
			response.setData(wareHouses);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouse>> updateWareHouse(WareHouse wareHouse) {
		
		if (wareHouse.getWareHouseId() == null) {
			throw new RuntimeException("Id must be passed to update a record");
		}
		
		Optional<WareHouse> opt = wareHouseRepository.findById(wareHouse.getWareHouseId());
		
		ResponseStructure<WareHouse> response = new ResponseStructure<WareHouse>();
		
		if (opt.isPresent()) {
			
			if (wareHouseRepository.existsByContactNumber(wareHouse.getContactNumber())) {
				throw new RuntimeException("Contact Number already exists");
			}
			
			if (!wareHouse.getContactNumber().matches("\\d{10}")) {
				throw new RuntimeException("Contact number must be exactly 10 digits");
			}
			
			wareHouseRepository.save(wareHouse);
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse updated successfully");
			response.setData(wareHouse);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("WareHouse record with Id: " + wareHouse.getWareHouseId() + " does not exists");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteWareHouse(Integer wareHouseId) {
		
		Optional<WareHouse> wareHouse = wareHouseRepository.findById(wareHouseId);
		
		ResponseStructure<String> response = new ResponseStructure<String>();
		
		if (wareHouse.isPresent()) {
			
			if (shipmentRepository.existsByWareHouseWareHouseId(wareHouseId)) {
				throw new IllegalArgumentException("WareHouse cannot be deleted because shipment already exixts");
			}
			
			wareHouseRepository.delete(wareHouse.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("WareHouse deleted successfully");
			response.setData("Success");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}
}
