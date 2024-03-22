package com.doctor.health.backendapi.authentication.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.doctor.health.backendapi.authentication.models.User;

@Repository 
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
} 
