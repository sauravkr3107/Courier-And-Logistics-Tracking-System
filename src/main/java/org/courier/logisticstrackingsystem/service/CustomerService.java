package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Map;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer);
	
	public ResponseEntity<ResponseStructure<List<Customer>>>getAllCustomer();
	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(Integer customerId);
	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(String email);
	
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Integer customerId, Map<String, Object> map);
	
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(Integer customerId);
	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(String customerPhone);
	
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(Integer pageNumber, Integer pageSize, String fieldName);
	
}
