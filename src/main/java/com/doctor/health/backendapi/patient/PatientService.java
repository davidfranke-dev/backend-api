package com.doctor.health.backendapi.patient;

import java.util.List;
import java.util.Optional;
import com.doctor.health.backendapi.patient.PatientMapper;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PatientMapper mapper;
    private MongoTemplate mongoTemplate;


    public Patient signUpPatient(Patient patient) {
        Patient createdPatient = createPatient(patient);
        return createdPatient;
    }

    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getSinglePatient(ObjectId id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        Patient createdPatient = patientRepository.insert(patient);
        return createdPatient;
    }

    public Patient updatePatient(ObjectId id, PatientDto patient) {
        Patient updatedPatient = patientRepository.findById(id).orElseThrow();
        mapper.updatePatientFromDto(patient, updatedPatient);
        patientRepository.save(updatedPatient);
        return updatedPatient;
    } 
     

    public Patient deletePatient(ObjectId id) {
        Query select = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(select, Patient.class); 
    }

    public PatientService(PatientMapper mapper, PatientRepository patientRepository, MongoTemplate mongoTemplate) {
        this.mapper = mapper;
        this.patientRepository = patientRepository;
        this.mongoTemplate = mongoTemplate;
    }
}
