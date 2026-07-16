package org.courier.logisticstrackingsystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
public class DeliveryAgent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deliveryAgentId;
	private String deliveryAgentName;
	private String deliveryAgentPhone;
	private String vehicleNumber;
	private Boolean availablityStatus;
	private Integer rating;
	
	@JsonIgnore
	@OneToMany(mappedBy = "deliveryAgent", cascade = CascadeType.ALL)
	private List<Shipment> shipments;
}
