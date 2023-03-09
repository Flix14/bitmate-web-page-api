package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.ContactEntity;
import com.bitmate.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public ResponseEntity<List<ContactEntity>> findAll() {
		List<ContactEntity> services = contactRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<ContactEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<ContactEntity> findById(Long id) {
		ContactEntity service = contactRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ContactEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<ContactEntity> updateContact(Long id, ContactEntity contact) {
		ContactEntity existingContact = contactRepository.findById(id).orElse(null);

		if (existingContact != null) {
			existingContact.setContact_preference(contact.getContact_preference());
			existingContact.setEmail(contact.getEmail());
			existingContact.setMessage(contact.getMessage());
			existingContact.setName(contact.getName());
			existingContact.setPhone(contact.getPhone());
			existingContact.setSubject(contact.getSubject());
			try {
				ContactEntity savedService = contactRepository.save(existingContact);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new ContactEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<ContactEntity> save(ContactEntity service) {
		try {
			ContactEntity savedService = contactRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			contactRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
