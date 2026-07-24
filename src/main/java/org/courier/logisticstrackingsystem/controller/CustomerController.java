package org.courier.logisticstrackingsystem.controller;

import java.util.List;
import java.util.Map;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Customer;
import org.courier.logisticstrackingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@GetMapping("/customerId/{customerId}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer customerId) {
		return customerService.getCustomerById(customerId);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(@PathVariable String email) {
		return customerService.getCustomerByEmail(email);
	}

	@PatchMapping("/customerId/{customerId}")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable Integer customerId,
			@RequestBody Map<String, Object> map) {
		return customerService.updateCustomer(customerId, map);
	}

	@DeleteMapping("/customerId/{customerId}")
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable Integer customerId) {
		return customerService.deleteCustomer(customerId);
	}

	@GetMapping("/contact/{customerPhone}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(@PathVariable String customerPhone) {
		return customerService.getCustomerByContact(customerPhone);
	}

	@GetMapping("/page/{pageNumber}/{pageSize}/{fieldName}")
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String fieldName) {
		return customerService.getCustomerByPaginationAndSorting(pageNumber, pageSize, fieldName);
	}

}
