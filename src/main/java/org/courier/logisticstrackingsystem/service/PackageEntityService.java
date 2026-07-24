package org.courier.logisticstrackingsystem.service;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.PackageEntity;
import org.courier.logisticstrackingsystem.entity.PackageType;
import org.springframework.http.ResponseEntity;

public interface PackageEntityService {
	
	public ResponseEntity<ResponseStructure<List<PackageEntity>>> getAllPackage();
	
	public ResponseEntity<ResponseStructure<PackageEntity>> getPackageById(Integer packageEntityId);
	
	public ResponseEntity<ResponseStructure<List<PackageEntity>>> getPackageByType(PackageType type);

}
