package com.doctor.health.backendapi.controller;

import org.springframework.web.bind.annotation.*;

import com.doctor.health.backendapi.appointment.Appointment;
import com.doctor.health.backendapi.appointment.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

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
        return new ResponseEntity<Optional<Appointment>>(appointmentService.getSingleAppointment(id), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/available")
    public ResponseEntity<List<LocalDateTime>> getAvailableAppointments(@PathVariable ObjectId categoryId) {
        System.out.println("Request received.");
        return new ResponseEntity<List<LocalDateTime>>(appointmentService.getAvailableAppointments(categoryId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<Appointment>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
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
