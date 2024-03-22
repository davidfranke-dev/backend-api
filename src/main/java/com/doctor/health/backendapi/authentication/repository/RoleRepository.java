package com.doctor.health.backendapi.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.doctor.health.backendapi.authentication.models.ERole;
import com.doctor.health.backendapi.authentication.models.Role;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
 