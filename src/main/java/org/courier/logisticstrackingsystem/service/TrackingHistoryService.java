package org.courier.logisticstrackingsystem.service;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.TrackingHistory;
import org.courier.logisticstrackingsystem.entity.TrackingHistoryStatus;
import org.springframework.http.ResponseEntity;

public interface TrackingHistoryService {
	
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistory();
	
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryById(Integer trackingHistoryId);
	
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryByTrackingNumber(String trackingNumber);
	
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByTrackingHistoryStatus(TrackingHistoryStatus trackingHistoryStatus);
}
