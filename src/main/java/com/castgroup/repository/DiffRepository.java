package com.castgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castgroup.entity.JsonBase64;


@Repository
public interface DiffRepository extends JpaRepository<JsonBase64, Integer>{
}

