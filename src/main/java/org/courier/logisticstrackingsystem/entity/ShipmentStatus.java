package org.courier.logisticstrackingsystem.entity;

public enum ShipmentStatus {
	ORDER_PLACED,
    PICKED_UP,
    IN_TRANSIT,
    ARRIVED_AT_HUB,
    OUT_FOR_DELIVERY,
    DELIVERED,
    FAILED_DELIVERY,
    RETURN_INITIATED,
    RETURNED,
    CANCELLED
}
