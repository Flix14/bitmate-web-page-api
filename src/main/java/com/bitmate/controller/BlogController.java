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

import com.bitmate.entity.BlogEntity;
import com.bitmate.service.BlogService;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    @GetMapping("")
    public ResponseEntity<List<BlogEntity>> getAllBlogs() {
        return blogService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BlogEntity> getBlogById(@PathVariable Long id) {
        return blogService.findById(id);
    }
    
    @PostMapping("")
    public ResponseEntity<BlogEntity> addBlog(@RequestBody BlogEntity blog) {
        return blogService.save(blog);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BlogEntity> updateBlog(@PathVariable Long id, @RequestBody BlogEntity blog) {
        return blogService.updateBlog(id, blog);
    }
    
    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
    }
}
