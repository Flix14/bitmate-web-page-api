package com.bitmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitmate.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

}
