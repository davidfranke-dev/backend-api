package com.doctor.health.backendapi.controller;

import org.springframework.web.bind.annotation.*;

import com.doctor.health.backendapi.appointment.Appointment;
import com.doctor.health.backendapi.appointment.AppointmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;


    @GetMapping
    public ResponseEntity<List<Appointment>> allAppointments() {
        return new ResponseEntity<List<Appointment>>(appointmentService.allAppointments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable ObjectId id) {
        System.out.println(id);
        return new ResponseEntity<Optional<Appointment>>(appointmentService.getSingleAppointment(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Appointment>(appointmentService.createAppointment(payload), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}") 
    public ResponseEntity<Appointment> updateAppointment(@PathVariable ObjectId id, @RequestBody Map<String, String> payload) {
        return new ResponseEntity<Appointment>(appointmentService.updateAppointment(id, payload), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable ObjectId id) {
        return new ResponseEntity<Appointment>(appointmentService.deleteAppointment(id), HttpStatus.OK);
    }

}
