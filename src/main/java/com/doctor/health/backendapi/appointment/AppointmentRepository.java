package com.doctor.health.backendapi.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository    
public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {
    
}
