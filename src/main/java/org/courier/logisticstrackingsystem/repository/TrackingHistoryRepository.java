package org.courier.logisticstrackingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.entity.TrackingHistory;
import org.courier.logisticstrackingsystem.entity.TrackingHistoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Integer>{

	Optional<TrackingHistory> findByShipmentTrackingNumber(String trackingNumber);

	List<TrackingHistory> findByTrackingHistoryStatus(TrackingHistoryStatus trackingHistoryStatus);

}
