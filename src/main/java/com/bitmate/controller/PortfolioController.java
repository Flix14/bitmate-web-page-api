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

import com.bitmate.entity.PortfolioEntity;
import com.bitmate.service.PortfolioService;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {
    
    @Autowired
    private PortfolioService portfolioService;
    
    @GetMapping("")
    public ResponseEntity<List<PortfolioEntity>> getAllPortfolios() {
        return portfolioService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PortfolioEntity> getPortfolioById(@PathVariable Long id) {
        return portfolioService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<PortfolioEntity> addPortfolio(@RequestBody PortfolioEntity portfolio) {
        return portfolioService.save(portfolio);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PortfolioEntity> updatePortfolio(@PathVariable Long id, @RequestBody PortfolioEntity portfolio) {
        return portfolioService.updatePortfolio(id, portfolio);
    }
    
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable Long id) {
        portfolioService.deleteById(id);
    }
}
