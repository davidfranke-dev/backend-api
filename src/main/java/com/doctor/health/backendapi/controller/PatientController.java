package com.doctor.health.backendapi.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.health.backendapi.patient.Patient;
import com.doctor.health.backendapi.patient.PatientDto;
import com.doctor.health.backendapi.patient.PatientService;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;


    @GetMapping
    public ResponseEntity<List<Patient>> allPatients() {
        return new ResponseEntity<List<Patient>>(patientService.allPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatient(@PathVariable ObjectId id) {
        System.out.println(id);
        return new ResponseEntity<Optional<Patient>>(patientService.getSinglePatient(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return new ResponseEntity<Patient>(patientService.createPatient(patient), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}") 
    public ResponseEntity<Patient> updatePatient(@PathVariable ObjectId id, @RequestBody PatientDto patient) {
        return new ResponseEntity<Patient>(patientService.updatePatient(id, patient), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable ObjectId id) {
        return new ResponseEntity<Patient>(patientService.deletePatient(id), HttpStatus.OK);
    }
}
