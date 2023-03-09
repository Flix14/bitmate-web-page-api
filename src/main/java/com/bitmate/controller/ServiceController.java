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

import com.bitmate.entity.ServiceEntity;
import com.bitmate.service.ServiceService;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    
    @Autowired
    private ServiceService serviceService;
    
    @GetMapping("")
    public ResponseEntity<List<ServiceEntity>> getAllServices() {
        return serviceService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable Long id) {
        return serviceService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<ServiceEntity> addService(@RequestBody ServiceEntity service) {
        return serviceService.save(service);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateService(@PathVariable Long id, @RequestBody ServiceEntity service) {
        return serviceService.updateService(id, service);
    }
    
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteById(id);
    }
}
