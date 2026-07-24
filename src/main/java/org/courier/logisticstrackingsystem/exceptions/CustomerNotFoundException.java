package org.courier.logisticstrackingsystem.exceptions;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
