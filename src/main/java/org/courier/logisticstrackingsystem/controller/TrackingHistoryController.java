package org.courier.logisticstrackingsystem.controller;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.TrackingHistory;
import org.courier.logisticstrackingsystem.entity.TrackingHistoryStatus;
import org.courier.logisticstrackingsystem.service.TrackingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trackingHistory")
public class TrackingHistoryController {

	@Autowired
	private TrackingHistoryService trackingHistoryService;

	@GetMapping
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistory() {
		return trackingHistoryService.getAllTrackingHistory();
	}

	@GetMapping("/trackingHistoryId/{trackingHistoryId}")
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryById(
			@PathVariable Integer trackingHistoryId) {
		return trackingHistoryService.getTrackingHistoryById(trackingHistoryId);
	}

	@GetMapping("/trackingNumber/{trackingNumber}")
	public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryByTrackingNumber(
			@PathVariable String trackingNumber) {
		return trackingHistoryService.getTrackingHistoryByTrackingNumber(trackingNumber);
	}

	@GetMapping("/trackingHistoryStatus/{trackingHistoryStatus}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getTrackingHistoryByTrackingHistoryStatus(
			@PathVariable TrackingHistoryStatus trackingHistoryStatus) {
		return trackingHistoryService.getTrackingHistoryByTrackingHistoryStatus(trackingHistoryStatus);
	}
}
