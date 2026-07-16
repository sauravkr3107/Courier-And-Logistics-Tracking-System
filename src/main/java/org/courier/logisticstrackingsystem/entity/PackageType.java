package org.courier.logisticstrackingsystem.entity;

public enum PackageType {
	PACKED,
    READY_FOR_PICKUP,
    PICKED_UP,
    IN_TRANSIT,
    AT_HUB,
    OUT_FOR_DELIVERY,
    DELIVERED,
    DAMAGED,
    LOST,
    RETURNED
}
