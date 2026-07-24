package org.courier.logisticstrackingsystem.repository;

import java.util.List;

import org.courier.logisticstrackingsystem.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer>{

	List<WareHouse> findByLocation(String location);

	List<WareHouse> findByCapacity(Integer capacity);

	List<WareHouse> findByCapacityGreaterThan(Integer value);

	boolean existsByContactNumber(String contactNumber);

}
