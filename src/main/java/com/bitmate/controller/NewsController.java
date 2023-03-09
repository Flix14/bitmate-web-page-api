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

import com.bitmate.entity.NewsEntity;
import com.bitmate.service.NewsService;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;
    
    @GetMapping("")
    public ResponseEntity<List<NewsEntity>> getAllNewss() {
        return newsService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NewsEntity> getNewsById(@PathVariable Long id) {
        return newsService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<NewsEntity> addNews(@RequestBody NewsEntity news) {
        return newsService.save(news);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NewsEntity> updateNews(@PathVariable Long id, @RequestBody NewsEntity news) {
        return newsService.updateNews(id, news);
    }
    
    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteById(id);
    }
}
