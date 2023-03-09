package com.bitmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitmate.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long>{

}
