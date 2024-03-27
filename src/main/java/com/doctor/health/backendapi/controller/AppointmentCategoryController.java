package com.doctor.health.backendapi.controller;


import com.doctor.health.backendapi.appointmentCategories.AppointmentCategory;
import com.doctor.health.backendapi.appointmentCategories.AppointmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/appointments/categories")
public class AppointmentCategoryController {

    @Autowired
    AppointmentCategoryService appointmentCategoryService;

    @GetMapping
    public ResponseEntity<List<AppointmentCategory>> getAppointmentCategories() {
        return new ResponseEntity<List<AppointmentCategory>>(appointmentCategoryService.allAppointmentCategories(), HttpStatus.OK);
    }

    // Add Create, Update, Delete
}
