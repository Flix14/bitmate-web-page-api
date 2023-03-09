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

import com.bitmate.entity.HomePageEntity;
import com.bitmate.service.HomePageService;

@RestController
@RequestMapping("/api/v1/homePages")
public class HomePageController {
    
    @Autowired
    private HomePageService homePageService;
    
    @GetMapping("")
    public ResponseEntity<List<HomePageEntity>> getAllHomePages() {
        return homePageService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HomePageEntity> getHomePageById(@PathVariable Long id) {
        return homePageService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<HomePageEntity> addHomePage(@RequestBody HomePageEntity homePage) {
        return homePageService.save(homePage);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HomePageEntity> updateHomePage(@PathVariable Long id, @RequestBody HomePageEntity homePage) {
        return homePageService.updateHomePage(id, homePage);
    }
    
    @DeleteMapping("/{id}")
    public void deleteHomePage(@PathVariable Long id) {
        homePageService.deleteById(id);
    }
}
