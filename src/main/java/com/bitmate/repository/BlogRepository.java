package com.bitmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitmate.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long>{

}
