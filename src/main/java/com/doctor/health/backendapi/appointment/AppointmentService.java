package com.doctor.health.backendapi.appointment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Appointment> allAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getSingleAppointment(ObjectId id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Map<String, String> payload) {
        Appointment appointment = appointmentRepository.insert(new Appointment(payload.get("endTime"), payload.get("startTime"), payload.get("patientId"), payload.get("doctorId"), payload.get("subject")));
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
}
