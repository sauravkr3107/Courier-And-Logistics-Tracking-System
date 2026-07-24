package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.TrackingHistory;
import org.courier.logisticstrackingsystem.entity.TrackingHistoryStatus;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.exceptions.TrackingNumberNotFoundException;
import org.courier.logisticstrackingsystem.repository.TrackingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrackingHistoryServiceImpl implements TrackingHistoryService {
	
	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;

	@Override
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistory() {
		
		List<TrackingHistory> trackingHistories = trackingHistoryRepository.findAll();
		
		ResponseStructure<List<TrackingHistory>> response = new ResponseStructure<List<TrackingHistory>>();
		
		if (!trackingHistories.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("TrackingHistories are retrieved successfully");
			response.setData(trackingHistories);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryById(Integer trackingHistoryId) {
		
		Optional<TrackingHistory> trackingHistory = trackingHistoryRepository.findById(trackingHistoryId);
		
		ResponseStructure<TrackingHistory> response = new ResponseStructure<TrackingHistory>();
		
		if (trackingHistory.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("TrackingHistory retrieved successfully");
			response.setData(trackingHistory.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryByTrackingNumber(
			String trackingNumber) {
		
		Optional<TrackingHistory> trackingHistory = trackingHistoryRepository.findByShipmentTrackingNumber(trackingNumber);
		
		ResponseStructure<TrackingHistory> response = new ResponseStructure<TrackingHistory>();
		
		if (trackingHistory.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("TrackingHistory retrieved successfully");
			response.setData(trackingHistory.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new TrackingNumberNotFoundException("Tracking Number is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByTrackingHistoryStatus(
			TrackingHistoryStatus trackingHistoryStatus) {
		
		List<TrackingHistory> trackingHistories = trackingHistoryRepository.findByTrackingHistoryStatus(trackingHistoryStatus);
		
		ResponseStructure<List<TrackingHistory>> response = new ResponseStructure<List<TrackingHistory>>();
		
		if (!trackingHistories.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("TrackingHistoey retrieved successfully");
			response.setData(trackingHistories);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}
	
	
}
