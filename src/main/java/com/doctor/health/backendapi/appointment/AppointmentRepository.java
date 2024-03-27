package com.doctor.health.backendapi.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository    
public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {
    List<Appointment> findByAppointmentCategoryId(ObjectId appointmentCategoryId);


    // Finds all appointments booked for the day
    List<Appointment> findByDoctorIdInAndStartTimeAfterAndEndTimeBeforeOrderByStartTime(List<ObjectId> doctorIds, LocalDateTime openingTime, LocalDateTime closingTime);

    List<Appointment> findFirst3ByDoctorIdIn(List<ObjectId> doctorIds);
}
