package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.PortfolioEntity;
import com.bitmate.repository.PortfolioRepository;

@Service
public class PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepository;
	
	public ResponseEntity<List<PortfolioEntity>> findAll() {
		List<PortfolioEntity> services = portfolioRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<PortfolioEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<PortfolioEntity> findById(Long id) {
		PortfolioEntity service = portfolioRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new PortfolioEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<PortfolioEntity> updatePortfolio(Long id, PortfolioEntity portfolio) {
		PortfolioEntity existingPortfolio = portfolioRepository.findById(id).orElse(null);

		if (existingPortfolio != null) {
			existingPortfolio.setDescription(portfolio.getDescription());
			existingPortfolio.setImg_url(portfolio.getImg_url());
			existingPortfolio.setProject_url(portfolio.getProject_url());
			existingPortfolio.setTitle(portfolio.getTitle());
			try {
				PortfolioEntity savedService = portfolioRepository.save(existingPortfolio);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new PortfolioEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<PortfolioEntity> save(PortfolioEntity service) {
		try {
			PortfolioEntity savedService = portfolioRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			portfolioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
