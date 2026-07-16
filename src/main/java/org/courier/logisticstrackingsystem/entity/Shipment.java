package org.courier.logisticstrackingsystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shipmentId;
	private String trackingNumber;
	private String source;
	private String destination;
	private Double weight;
	@CreationTimestamp
	private LocalDateTime shipmentDateTime;
	private LocalDate deliveryDate;
	private ShipmentStatus shipmentStatus;
	
	@JoinColumn
	@ManyToOne
	private Customer customer;
	
	@JsonIgnore
	@OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
	private Payment payment;
	
	@JoinColumn
	@ManyToOne
	private DeliveryAgent deliveryAgent;
	
	@JsonIgnore
	@OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
	private PackageEntity packageEntity;
	
	@JoinColumn
	@ManyToOne
	private WareHouse wareHouse;
	
	@JsonIgnore
	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	private List<TrackingHistory> trackingHistories;
}
