package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Payment;
import org.courier.logisticstrackingsystem.entity.PaymentStatus;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		
		List<Payment> payments = paymentRepository.findAll();
		
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		
		if (!payments.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment retrieved successfully");
			response.setData(payments);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByPaymentId(Integer paymentId) {
		
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		
		if (payment.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment retrieved successfully");
			response.setData(payment.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentByPaymentStatus(Integer paymentId, PaymentStatus paymentStatus) {
		
		Optional<Payment> opt = paymentRepository.findById(paymentId);
		
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		
		if (opt.isPresent()) {
			Payment payment = opt.get();
			payment.setPaymentStatus(paymentStatus);
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment status records updated successfully");
			response.setData(paymentRepository.save(payment));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deletePayment(Integer paymentId) {
		
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		
		ResponseStructure<String> response = new ResponseStructure<String>();
		
		if (payment.isPresent()) {
			paymentRepository.delete(payment.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment deleted successfully");
			response.setData("Success");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}
}
