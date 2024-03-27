package com.doctor.health.backendapi.appointmentCategories;


import com.doctor.health.backendapi.doctor.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "appointmentCategories")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentCategory {

    @Id
    private ObjectId id;

    private String name;

    private String description;

    @DocumentReference
    private List<Doctor> doctors;

}
