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

import com.bitmate.entity.ContactEntity;
import com.bitmate.service.ContactService;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @GetMapping("")
    public ResponseEntity<List<ContactEntity>> getAllContacts() {
        return contactService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContactEntity> getContactById(@PathVariable Long id) {
        return contactService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<ContactEntity> addContact(@RequestBody ContactEntity contact) {
        return contactService.save(contact);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ContactEntity> updateContact(@PathVariable Long id, @RequestBody ContactEntity contact) {
        return contactService.updateContact(id, contact);
    }
    
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
    }
}
