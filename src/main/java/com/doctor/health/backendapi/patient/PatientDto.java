package com.doctor.health.backendapi.patient;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String birthday;
    private Character gender;
    private Double height;
    private Double weight;
    private List<Integer> allergies;
    private List<Integer> conditions;
}
