package org.courier.logisticstrackingsystem.controller;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.PackageEntity;
import org.courier.logisticstrackingsystem.entity.PackageType;
import org.courier.logisticstrackingsystem.service.PackageEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packageEntity")
public class PackageEntityController {
	
	@Autowired
	private PackageEntityService packageEntityService;
	
	@GetMapping
    public ResponseEntity<ResponseStructure<List<PackageEntity>>> getAllPackage() {
    	return packageEntityService.getAllPackage();
    }
	
	@GetMapping("/packageEntityId/{packageEntityId}")
	public ResponseEntity<ResponseStructure<PackageEntity>> getPackageById(@PathVariable Integer packageEntityId) {
		return packageEntityService.getPackageById(packageEntityId);
	}
	
	@GetMapping("/packageType/{type}")
	public ResponseEntity<ResponseStructure<List<PackageEntity>>> getPackageByType(@PathVariable PackageType type) {
		return packageEntityService.getPackageByType(type);
	}

}
