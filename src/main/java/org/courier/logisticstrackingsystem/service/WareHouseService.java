package org.courier.logisticstrackingsystem.service;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.WareHouse;
import org.springframework.http.ResponseEntity;

public interface WareHouseService {
	
	public ResponseEntity<ResponseStructure<WareHouse>> saveWareHouse(WareHouse wareHouse);
	
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getAllWareHouse();
	
	public ResponseEntity<ResponseStructure<WareHouse>> getWareHouseByWareHouseId(Integer wareHouseId);
	
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByLocation(String location);
	
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByCapacity(Integer capacity);
	
	public ResponseEntity<ResponseStructure<List<WareHouse>>> getWareHouseByCapacityGreaterThan(Integer value);
	
	public ResponseEntity<ResponseStructure<WareHouse>> updateWareHouse(WareHouse wareHouse);
	
	public ResponseEntity<ResponseStructure<String>> deleteWareHouse(Integer wareHouseId);
	
}
