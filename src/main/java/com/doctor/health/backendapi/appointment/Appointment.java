package com.doctor.health.backendapi.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    
    @Id
    private ObjectId id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String patientId;

    private String doctorId;

    private String subject;

    private ObjectId appointmentCategoryId;

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, String patientId, String doctorId, String subject, ObjectId appointmentCategoryId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.subject = subject;
        this.appointmentCategoryId = appointmentCategoryId;
    }
}   
