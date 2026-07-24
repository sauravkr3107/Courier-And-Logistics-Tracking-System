package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.Customer;
import org.courier.logisticstrackingsystem.exceptions.ContactNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.EmailNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.repository.CustomerRepository;
import org.courier.logisticstrackingsystem.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;

	@Override
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		
		if (customerRepository.existsByCustomerPhone(customer.getCustomerPhone())) {
			throw new RuntimeException("Phone Number already exixts");
		}
		
		if (!customer.getCustomerPhone().matches("\\d{10}")) {
			throw new RuntimeException("Contact number must be exactly 10 digits");
		}
		
		Customer saveCustomer = customerRepository.save(customer);
		
		ResponseStructure<Customer> response = new ResponseStructure<Customer>();
		
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Customer saved successfully");
		response.setData(saveCustomer);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		
		List<Customer> customer = customerRepository.findAll();
		
		ResponseStructure<List<Customer>> response = new ResponseStructure<List<Customer>>();
	
		if (!customer.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All records retrieved successfully");
			response.setData(customer);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No records are available");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(Integer customerId) {
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		ResponseStructure<Customer> response = new ResponseStructure<Customer>();
		
		if (customer.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Record retrieved successfully");
			response.setData(customer.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(String email) {
		
		Optional<Customer> customer = customerRepository.findCustomerByEmail(email);
		
		ResponseStructure<Customer> response = new ResponseStructure<Customer>();
		
		if (customer.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Record retrieved successfully");
			response.setData(customer.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new EmailNotFoundException("Email is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Integer customerId, Map<String, Object> map) {
		
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		ResponseStructure<Customer> response = new ResponseStructure<Customer>();
		
		if (opt.isPresent()) {
			Customer customer = opt.get();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				
				switch (key) {
				case "customerName":
					customer.setCustomerName((String) value);
					break;
				case "email":
					if (customerRepository.existsByEmail(customer.getEmail())) {
						throw new RuntimeException("Email already exists");
					}
					
					customer.setEmail((String) value);
					break;
				case "customerPhone":
					if (customerRepository.existsByCustomerPhone(customer.getCustomerPhone())) {
						throw new RuntimeException("Phone Number already exixts");
					}
					
					if (!customer.getCustomerPhone().matches("\\d{10}")) {
						throw new RuntimeException("Contact number must be exactly 10 digits");
					}
	
					customer.setCustomerPhone((String) value);
					break;
				case "address":
					customer.setAddress((String) value);
					break;
				default:
					System.out.println(key + " is not available");
					break;
				}
			}
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Customer record with + " + customerId + " updated");
			response.setData(customerRepository.save(customer));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(Integer customerId) {
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		ResponseStructure<String> response = new ResponseStructure<String>();
		
		if (customer.isPresent()) {
			
			if (!shipmentRepository.existsByCustomerCustomerId(customerId)) {
				throw new IllegalArgumentException("Customer cannot be deleted because shipment does not exists");
			}
			
			customerRepository.delete(customer.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Customer record deleted successfully");
			response.setData("Success");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(String customerPhone) {
		
		Optional<Customer> customer = customerRepository.findByCustomerPhone(customerPhone);
		
		ResponseStructure<Customer> response = new ResponseStructure<Customer>();
		
		if (customer.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Customer record retrieved successfully");
			response.setData(customer.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new ContactNotFoundException("Contact is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(Integer pageNumber,
			Integer pageSize, String fieldName) {
		
		Page<Customer> page = customerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(fieldName).descending()));
		
		ResponseStructure<Page<Customer>> response = new ResponseStructure<Page<Customer>>();
		
		if (!page.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Customer record retrieved from the respective page");
			response.setData(page);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No record is available");
		}
	}
}
