package org.acme.sw.onboarding.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.acme.sw.onboarding.model.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorService {

    private static final String DOCTOR_DATA_PATH = "/data/doctors.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private static final DoctorService INSTANCE = new DoctorService();
    private final List<Doctor> doctors;

    public DoctorService() {
        this.doctors = new ArrayList<>();
        this.populate();
    }

    public static DoctorService get() {
        return INSTANCE;
    }

    private void populate() {
        try {
            List<Doctor> doctors = new ObjectMapper().readValue(this.getClass().getResourceAsStream(DOCTOR_DATA_PATH), new TypeReference<>() {
            });
            this.doctors.addAll(doctors);
            LOGGER.info("Predefined data from Doctors have been populated");
        } catch (IOException ex) {
            throw new IllegalStateException("Problem while populating DoctorService with JSON predefined data", ex);
        }
    }

    public List<Doctor> getDoctors() {
        return this.doctors;
    }
}
