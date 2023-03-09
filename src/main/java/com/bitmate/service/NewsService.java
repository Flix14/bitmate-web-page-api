package com.bitmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitmate.entity.NewsEntity;
import com.bitmate.repository.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	public ResponseEntity<List<NewsEntity>> findAll() {
		List<NewsEntity> services = newsRepository.findAll();

        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<NewsEntity>(), HttpStatus.NO_CONTENT);
        }
	}
	
	public ResponseEntity<NewsEntity> findById(Long id) {
		NewsEntity service = newsRepository.findById(id).orElse(null);

        if (service != null) {
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new NewsEntity() ,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<NewsEntity> updateNews(Long id, NewsEntity news) {
		NewsEntity existingNews = newsRepository.findById(id).orElse(null);

		if (existingNews != null) {
			existingNews.setSource(news.getSource());
			existingNews.setContent(news.getContent());
			existingNews.setDate_created(news.getDate_created());	
			existingNews.setTitle(news.getTitle());
			try {
				NewsEntity savedService = newsRepository.save(existingNews);
            	return new ResponseEntity<>(savedService, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
        } else {
            return new ResponseEntity<>(new NewsEntity() ,HttpStatus.NOT_FOUND);
        }
	}
	
	public ResponseEntity<NewsEntity> save(NewsEntity service) {
		try {
			NewsEntity savedService = newsRepository.save(service);
			return new ResponseEntity<>(savedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Void> deleteById(Long id) {
		try {
			newsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
