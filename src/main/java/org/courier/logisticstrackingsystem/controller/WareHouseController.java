package org.courier.logisticstrackingsystem.controller;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.WareHouse;
import org.courier.logisticstrackingsystem.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wareHouse")
public class WareHouseController {
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<WareHouse>> saveWareHouse(@RequestBody WareHouse wareHouse) {
		return wareHouseService.saveWareHouse(wareHouse);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getAllWareHouse() {
		return wareHouseService.getAllWareHouse();
	}
	
	@GetMapping("/wareHouseId/{wareHouseId}")
	public ResponseEntity<ResponseStructure<WareHouse>> getWareHouseByWareHouseId(@PathVariable Integer wareHouseId) {
		return wareHouseService.getWareHouseByWareHouseId(wareHouseId);
	}
	
	@GetMapping("/location/{location}")
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByLocation(@PathVariable String location) {
		return wareHouseService.getWareHouseByLocation(location);
	}
	
	@GetMapping("/capacity/{capacity}")
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByCapacity(@PathVariable Integer capacity) {
		return wareHouseService.getWareHouseByCapacity(capacity);
	}
	
	@GetMapping("/capacityGreaterThan/{value}")
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByCapacityGreaterThan(@PathVariable Integer value) {
		return wareHouseService.getWareHouseByCapacityGreaterThan(value);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<WareHouse>> updateWareHouse(@RequestBody WareHouse wareHouse) {
		return wareHouseService.updateWareHouse(wareHouse);
	}
	
	@DeleteMapping("/wareHouseId/{wareHouseId}")
	public ResponseEntity<ResponseStructure<String>> deleteWareHouse(@PathVariable Integer wareHouseId) {
		return wareHouseService.deleteWareHouse(wareHouseId);
	}
}
