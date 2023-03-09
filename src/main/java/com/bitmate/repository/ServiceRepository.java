package com.bitmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitmate.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>{

}
