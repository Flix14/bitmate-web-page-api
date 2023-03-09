package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.TestimonialEntity;
import com.bitmate.repository.TestimonialRepository;

@Service
public class TestimonialService {

	@Autowired
	private TestimonialRepository testimonialRepository;
	
	public ResponseEntity<List<TestimonialEntity>> findAll() {
		List<TestimonialEntity> services = testimonialRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<TestimonialEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<TestimonialEntity> findById(Long id) {
		TestimonialEntity service = testimonialRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new TestimonialEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<TestimonialEntity> updateTestimonial(Long id, TestimonialEntity testimonial) {
		TestimonialEntity existingTestimonial = testimonialRepository.findById(id).orElse(null);

		if (existingTestimonial != null) {
			existingTestimonial.setImg_url(testimonial.getImg_url());
			existingTestimonial.setName(testimonial.getName());
			existingTestimonial.setTestimonial(testimonial.getTestimonial());
			try {
				TestimonialEntity savedService = testimonialRepository.save(existingTestimonial);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new TestimonialEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<TestimonialEntity> save(TestimonialEntity service) {
		try {
			TestimonialEntity savedService = testimonialRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			testimonialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
