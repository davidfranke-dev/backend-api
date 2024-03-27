package com.doctor.health.backendapi.doctor;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;


    public List<Doctor> allDoctors() {
        return doctorRepository.findAll();
    }

//    public List<Doctor> getSpecificDoctors(ObjectId) {
//        return doctorRepository.findBy
//    }



    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


}
