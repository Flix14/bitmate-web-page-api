package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.HomePageEntity;
import com.bitmate.repository.HomePageRepository;

@Service
public class HomePageService {

	@Autowired
	private HomePageRepository homePageRepository;
	
	public ResponseEntity<List<HomePageEntity>> findAll() {
		List<HomePageEntity> services = homePageRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<HomePageEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<HomePageEntity> findById(Long id) {
		HomePageEntity service = homePageRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HomePageEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<HomePageEntity> updateHomePage(Long id, HomePageEntity homePage) {
		HomePageEntity existingHomePage = homePageRepository.findById(id).orElse(null);

		if (existingHomePage != null) {
			existingHomePage.setDescription(homePage.getDescription());
			existingHomePage.setImg_url(homePage.getImg_url());
			existingHomePage.setSubtitle(homePage.getSubtitle());
			existingHomePage.setTitle(homePage.getTitle());
			try {
				HomePageEntity savedService = homePageRepository.save(existingHomePage);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new HomePageEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<HomePageEntity> save(HomePageEntity service) {
		try {
			HomePageEntity savedService = homePageRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			homePageRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
