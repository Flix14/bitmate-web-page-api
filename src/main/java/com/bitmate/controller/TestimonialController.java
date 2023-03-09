package com.bitmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitmate.entity.TestimonialEntity;
import com.bitmate.service.TestimonialService;

@RestController
@RequestMapping("/api/v1/testimonials")
public class TestimonialController {
    
    @Autowired
    private TestimonialService testimonialService;
    
    @GetMapping("")
    public ResponseEntity<List<TestimonialEntity>> getAllTestimonials() {
        return testimonialService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TestimonialEntity> getTestimonialById(@PathVariable Long id) {
        return testimonialService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<TestimonialEntity> addTestimonial(@RequestBody TestimonialEntity testimonial) {
        return testimonialService.save(testimonial);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TestimonialEntity> updateTestimonial(@PathVariable Long id, @RequestBody TestimonialEntity testimonial) {
        return testimonialService.updateTestimonial(id, testimonial);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTestimonial(@PathVariable Long id) {
        testimonialService.deleteById(id);
    }
}
