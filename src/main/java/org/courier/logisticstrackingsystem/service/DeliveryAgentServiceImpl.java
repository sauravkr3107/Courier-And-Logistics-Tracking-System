package org.courier.logisticstrackingsystem.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.courier.logisticstrackingsystem.dto.ResponseStructure;
import org.courier.logisticstrackingsystem.entity.DeliveryAgent;
import org.courier.logisticstrackingsystem.exceptions.ContactNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.IdNotFoundException;
import org.courier.logisticstrackingsystem.exceptions.NoRecordAvailableException;
import org.courier.logisticstrackingsystem.exceptions.VehicleNumberNotFoundException;
import org.courier.logisticstrackingsystem.repository.DeliveryAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;
	
	@Override
	public ResponseEntity<ResponseStructure<DeliveryAgent>> saveDeliveryAgent(DeliveryAgent deliveryAgent) {
		
		DeliveryAgent saveDeliveryAgent = deliveryAgentRepository.save(deliveryAgent);
		
		ResponseStructure<DeliveryAgent> response = new ResponseStructure<DeliveryAgent>();
		
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("DeliveryAgent created successfully");
		response.setData(saveDeliveryAgent);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllDeliveryAgent() {
		
		List<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findAll();
		
		ResponseStructure<List<DeliveryAgent>> response = new ResponseStructure<List<DeliveryAgent>>();
		
		if (!deliveryAgent.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent record retrieved successfully");
			response.setData(deliveryAgent);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No record is available");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentById(Integer deliveryAgentId) {
		
		Optional<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findById(deliveryAgentId);
		
		ResponseStructure<DeliveryAgent> response = new ResponseStructure<DeliveryAgent>();
		
		if (deliveryAgent.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent retrieved successfully");
			response.setData(deliveryAgent.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByVehicleNumber(String vehicleNumber) {
		
		Optional<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findByVehicleNumber(vehicleNumber);
		
		ResponseStructure<DeliveryAgent> response = new ResponseStructure<DeliveryAgent>();
		
		if (deliveryAgent.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent retrieved successfully");
			response.setData(deliveryAgent.get());
			
			return  new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new VehicleNumberNotFoundException("Vehicle Number is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<DeliveryAgent>> getDeliveryAgentByContact(String deliveryAgentPhone) {
		
		Optional<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findByDeliveryAgentPhone(deliveryAgentPhone);
		
		ResponseStructure<DeliveryAgent> response = new ResponseStructure<DeliveryAgent>();
		
		if (deliveryAgent.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent retrieved successfully");
			response.setData(deliveryAgent.get());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new ContactNotFoundException("Contact is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getDeliveryAgentByRatingGreaterThanValue(Integer rating) {
		
		List<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findByRatingGreaterThan(rating);
		
		ResponseStructure<List<DeliveryAgent>> response = new ResponseStructure<List<DeliveryAgent>>();
		
		if (!deliveryAgent.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent retrieved successfully");
			response.setData(deliveryAgent);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No record is available");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgent(Integer deliveryAgentId,
			Map<String, Object> map) {
		
		Optional<DeliveryAgent> opt = deliveryAgentRepository.findById(deliveryAgentId);
		
		ResponseStructure<DeliveryAgent> response = new ResponseStructure<DeliveryAgent>();
		
		if (opt.isPresent()) {
			DeliveryAgent deliveryAgent = opt.get();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				switch (key) {
				case "deliveryAgentName":
					deliveryAgent.setDeliveryAgentName((String) value);
					break;
				case "deliveryAgentPhone":
					deliveryAgent.setDeliveryAgentPhone((String) value);
					break;
				case "vehicleNumber":
					deliveryAgent.setVehicleNumber((String) value);
					break;
				case "availablityStatus":
					deliveryAgent.setAvailablityStatus((Boolean) value);
					break;
				case "rating":
					deliveryAgent.setRating((Integer) value);
					break;
				default:
					System.out.println(key + " is not available");
					break;
				}
			}
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent updated successfully");
			response.setData(deliveryAgentRepository.save(deliveryAgent));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteDeliveryAgent(Integer deliveryAgentId) {
		
		Optional<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findById(deliveryAgentId);
		
		ResponseStructure<String> response = new ResponseStructure<String>();
		
		if (deliveryAgent.isPresent()) {
			deliveryAgentRepository.delete(deliveryAgent.get());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent deleted successfully");
			response.setData("Success");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgentAvailablity(Integer deliveryAgentId) {
		
		Optional<DeliveryAgent> opt = deliveryAgentRepository.findById(deliveryAgentId);
		
		ResponseStructure<DeliveryAgent> response = new ResponseStructure<DeliveryAgent>();
		
		if (opt.isPresent()) {
			DeliveryAgent deliveryAgent = opt.get();
			
			deliveryAgent.setAvailablityStatus(!deliveryAgent.getAvailablityStatus());
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("DeliveryAgent updated successfully");
			response.setData(deliveryAgentRepository.save(deliveryAgent));
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id is not found");
		}
	}

}
