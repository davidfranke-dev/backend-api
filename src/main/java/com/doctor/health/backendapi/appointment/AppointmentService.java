package com.doctor.health.backendapi.appointment;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import com.doctor.health.backendapi.appointmentCategories.AppointmentCategory;
import com.doctor.health.backendapi.appointmentCategories.AppointmentCategoryRepository;
import com.doctor.health.backendapi.appointmentCategories.AppointmentCategoryService;
import com.doctor.health.backendapi.doctor.Doctor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    
    private AppointmentRepository appointmentRepository;
    private AppointmentCategoryRepository appointmentCategoryRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Appointment> allAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getSingleAppointment(ObjectId id) {
        return appointmentRepository.findById(id);
    }

    private List<LocalDateTime> getAvailableTime(List<Appointment> bookedAppointments, LocalDateTime currentTime, LocalDateTime closingTime) {
        LocalDateTime potentialStartTime = currentTime;
        List<LocalDateTime> potentialStartTimes = new ArrayList<>();

        for (int i = 0; i < bookedAppointments.size(); i++) {
            Appointment nextAppointment = bookedAppointments.get(i);

            if (nextAppointment.getEndTime().isBefore(potentialStartTime.plusMinutes(30)) ||
                    nextAppointment.getStartTime().isBefore(potentialStartTime.plusMinutes(30))) {
                potentialStartTime = nextAppointment.getEndTime();
                continue;
            }

            potentialStartTimes.add(potentialStartTime);
        }

        while (potentialStartTime.plusMinutes(30).isBefore(closingTime)) {
            potentialStartTimes.add(potentialStartTime);
            potentialStartTime = potentialStartTime.plusMinutes(30);
        }

        return potentialStartTimes;
    }

    public List<LocalDateTime> getAvailableAppointments(ObjectId categoryId) {
        int estimatedLength = 30;
        LocalTime openingTime = LocalTime.of(17, 0);
        LocalTime closingTime = LocalTime.of(17, 0);
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 3, 23, 14, 0); // Replace with .now() for final version
        LocalDateTime currentDateClosing = LocalDateTime.of(2024, 3, 23, 18, 0); // Replace with .now() for final version


        // Request all possible doctors
        AppointmentCategory appointmentCategory = appointmentCategoryRepository.findById(categoryId).orElse(null);

        // TODO: Build (better) exception handling
        if (appointmentCategory == null) {
            System.out.println("Appointment category is non-existent.");
            return null;
        } else {
            System.out.println("Appointment category seems to exist.");
        }

        List<ObjectId> doctorIds = appointmentCategory.getDoctors().stream().map(Doctor::getId).toList();
        System.out.println("Doctors:");
        System.out.println(doctorIds);

        List<Appointment> nextAppointments = appointmentRepository.findByDoctorIdInAndStartTimeAfterAndEndTimeBeforeOrderByStartTime(doctorIds, currentDateTime, currentDateClosing);
        System.out.println("Next Appointments:");
        System.out.println(nextAppointments);

        // Find available appointment
        List<LocalDateTime> availableAppointments = this.getAvailableTime(nextAppointments, currentDateTime, currentDateClosing);

        return availableAppointments;
    }


    public Appointment createAppointment(Appointment appointment) {
        appointmentRepository.insert(appointment);
        return appointment;
    }

    public Appointment updateAppointment(ObjectId id, Map<String, String> payload) {       
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        
        Update update = new Update();
        Query select = Query.query(Criteria.where("id").is(id));

        payload.forEach((key, value) -> {
            if (value != null) {
                update.set(key, value);
            }
        });


        // Please read about MongoDB options
        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);
        Appointment updatedAppointment = mongoTemplate.findAndModify(select, update, options, Appointment.class);
        
        return updatedAppointment;
    }

    public Appointment deleteAppointment(ObjectId id) {
        Query select = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(select, Appointment.class); 
    }

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentCategoryRepository appointmentCategoryRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentCategoryRepository = appointmentCategoryRepository;
    }
}
