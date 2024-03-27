package com.doctor.health.backendapi.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    @Id
    private ObjectId id;

    private String firstname;

    private String lastname;

    private String title;

    private String startServeTime;

    private String endServeTime;
}
