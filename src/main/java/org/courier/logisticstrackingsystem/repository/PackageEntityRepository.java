package org.courier.logisticstrackingsystem.repository;

import java.util.List;

import org.courier.logisticstrackingsystem.entity.PackageEntity;
import org.courier.logisticstrackingsystem.entity.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageEntityRepository extends JpaRepository<PackageEntity, Integer>{

	List<PackageEntity> findByPackageType(PackageType type);

}
