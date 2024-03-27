package com.doctor.health.backendapi.appointmentCategories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentCategoryRepository extends MongoRepository<AppointmentCategory, ObjectId> {
}
