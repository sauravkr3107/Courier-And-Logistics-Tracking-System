package org.courier.logisticstrackingsystem.service;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Payment;
import org.courier.logisticstrackingsystem.entity.PaymentStatus;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
	
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment();
	
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByPaymentId(Integer paymentId);
	
    public ResponseEntity<ResponseStructure<Payment>> updatePaymentByPaymentStatus(Integer paymentId, PaymentStatus paymentStatus);
    
    public ResponseEntity<ResponseStructure<String>> deletePayment(Integer paymentId);
}
