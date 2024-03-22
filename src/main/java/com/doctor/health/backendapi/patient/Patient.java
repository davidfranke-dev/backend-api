package com.doctor.health.backendapi.patient;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    
    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String birthday;

    private Character gender;

    private Double height;

    private Double weight;

    private List<Integer> allergies;

    private List<Integer> conditions;


    public Patient(String firstName, String lastName, Character gender, Double height, double weight, List<Integer> allergies, List<Integer> conditions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.allergies = allergies;
        this.conditions = conditions;
    }
}   
