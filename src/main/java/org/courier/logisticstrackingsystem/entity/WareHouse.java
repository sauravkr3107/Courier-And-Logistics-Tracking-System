package org.courier.logisticstrackingsystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wareHouseId;
	private String wareHouseName;
	private String location;
	private Integer capacity;
	private String contactNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy = "shipment")
	private List<WareHouse> wareHouses;
}
