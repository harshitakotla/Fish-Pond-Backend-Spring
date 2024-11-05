package com.admin.pondmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.admin.pondmanagement.model.Pond;

public interface PondRepository extends MongoRepository<Pond, String> {
}