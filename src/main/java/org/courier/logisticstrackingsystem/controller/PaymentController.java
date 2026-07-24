package org.courier.logisticstrackingsystem.controller;

import java.util.List;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Payment;
import org.courier.logisticstrackingsystem.entity.PaymentStatus;
import org.courier.logisticstrackingsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
    public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
	    return paymentService.getAllPayment();
    }
	
	@GetMapping("/paymentId/{paymentId}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByPaymentId(@PathVariable Integer paymentId) {
		return paymentService.getPaymentByPaymentId(paymentId);
	}
	
	@PatchMapping("/paymentId/{paymentId}/paymentStatus/{paymentStatus}")
    public ResponseEntity<ResponseStructure<Payment>> updatePaymentByPaymentStatus(@PathVariable Integer paymentId,@PathVariable PaymentStatus paymentStatus) {
    	return paymentService.updatePaymentByPaymentStatus(paymentId, paymentStatus);
    }
    
	@DeleteMapping("/paymentId/{paymentId}")
    public ResponseEntity<ResponseStructure<String>> deletePayment(@PathVariable Integer paymentId) {
    	return paymentService.deletePayment(paymentId);
    }
}
