package com.doctor.health.backendapi.appointmentCategories;

import com.doctor.health.backendapi.appointment.AppointmentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentCategoryService {

    @Autowired
    AppointmentCategoryRepository appointmentCategoryRepository;

    public List<AppointmentCategory> allAppointmentCategories() {
        return appointmentCategoryRepository.findAll();
    }

    public Optional<AppointmentCategory> getSingleAppointmentCategory(ObjectId id) {
        return appointmentCategoryRepository.findById(id);
    }

}
