package com.doctor.health.backendapi.appointment;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    
    @Id
    private ObjectId id;

    private String startTime;

    private String endTime;

    private String patientId;

    private String doctorId;

    private String subject;

    public Appointment(String startTime, String endTime, String patientId, String doctorId, String subject) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.subject = subject;
    }
}   
