package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.PackageEntity;
import org.courier.logisticstrackingsystem.entity.PackageType;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.repository.PackageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PackageEntityServiceImpl implements PackageEntityService {

	@Autowired
	private PackageEntityRepository packageEntityRepository;

	@Override
	public ResponseEntity<ResponseStructure<List<PackageEntity>>> getAllPackage() {
		List<PackageEntity> packages = packageEntityRepository.findAll();
		
		ResponseStructure<List<PackageEntity>> response = new ResponseStructure<List<PackageEntity>>();
		
		if (!packages.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("PackageEntity retrieved successfully");
			response.setData(packages);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<PackageEntity>> getPackageById(Integer packageEntityId) {
		
		Optional<PackageEntity> packageEntity = packageEntityRepository.findById(packageEntityId);
		
		ResponseStructure<PackageEntity> response = new ResponseStructure<PackageEntity>();
		
		if (packageEntity.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("PackageEntity retrieved successfully");
			response.setData(packageEntity.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<PackageEntity>>> getPackageByType(PackageType type) {
		
		List<PackageEntity> packageEntity = packageEntityRepository.findByPackageType(type);
		
		ResponseStructure<List<PackageEntity>> response = new ResponseStructure<List<PackageEntity>>();
		
		if (!packageEntity.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("PackageEntity retrieved successfully");
			response.setData(packageEntity);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}
}
