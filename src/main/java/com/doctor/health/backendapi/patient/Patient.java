package com.doctor.health.backendapi.patient;

import java.util.Date;
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

    private Date birthday;

    private String telephone;

    private String city;

    private String zip;

//    private Character gender;

//    private Double height;
//
//    private Double weight;

//    private List<Integer> allergies;
//
//    private List<Integer> conditions;


    public Patient(String firstName, String lastName, Date birthday, String city, String zip, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.city = city;
        this.zip = zip;
        this.telephone = telephone;
//        this.gender = gender;
//        this.height = height;
//        this.weight = weight;
//        this.allergies = allergies;
//        this.conditions = conditions;
    }
}   
