package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.BlogEntity;
import com.bitmate.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public ResponseEntity<List<BlogEntity>> findAll() {
		List<BlogEntity> services = blogRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<BlogEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<BlogEntity> findById(Long id) {
		BlogEntity service = blogRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BlogEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<BlogEntity> updateBlog(Long id, BlogEntity blog) {
		BlogEntity existingBlog = blogRepository.findById(id).orElse(null);

		if (existingBlog != null) {
			existingBlog.setAuthor(blog.getAuthor());
			existingBlog.setContent(blog.getContent());
			existingBlog.setDate_created(blog.getDate_created());	
			existingBlog.setTitle(blog.getTitle());
			try {
				BlogEntity savedService = blogRepository.save(existingBlog);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new BlogEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<BlogEntity> save(BlogEntity service) {
		try {
			BlogEntity savedService = blogRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			blogRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
