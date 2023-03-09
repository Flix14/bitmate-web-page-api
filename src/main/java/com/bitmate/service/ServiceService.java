package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.ServiceEntity;
import com.bitmate.repository.ServiceRepository;

@Service
public class ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;
	
	public ResponseEntity<List<ServiceEntity>> findAll() {
		List<ServiceEntity> services = serviceRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<ServiceEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<ServiceEntity> findById(Long id) {
		ServiceEntity service = serviceRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ServiceEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<ServiceEntity> updateService(Long id, ServiceEntity service) {
		ServiceEntity existingService = serviceRepository.findById(id).orElse(null);

		if (existingService != null) {
			existingService.setName(service.getName());
			existingService.setDescription(service.getDescription());
			existingService.setPrice(service.getPrice());
			existingService.setImg_url(service.getImg_url());
			try {
				ServiceEntity savedService = serviceRepository.save(existingService);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new ServiceEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<ServiceEntity> save(ServiceEntity service) {
		try {
			ServiceEntity savedService = serviceRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			serviceRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
