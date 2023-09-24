package org.acme.sw.onboarding.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.acme.sw.onboarding.model.SymptomSpecialty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SymptomSpecialtyService {

    private static final String SYMPTOMS_DATA_PATH = "/data/symptom_specialty.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private static final SymptomSpecialtyService INSTANCE = new SymptomSpecialtyService();
    private final List<SymptomSpecialty> symptomSpecialties;

    private SymptomSpecialtyService() {
        this.symptomSpecialties = new ArrayList<>();
        this.populate();
    }

    public static SymptomSpecialtyService get() {
        return INSTANCE;
    }

    private void populate() {
        try {
            List<SymptomSpecialty> symptomSpecialties = new ObjectMapper().readValue(this.getClass().getResourceAsStream(SYMPTOMS_DATA_PATH), new TypeReference<>() {
            });
            this.symptomSpecialties.addAll(symptomSpecialties);
            LOGGER.info("Predefined data from SymptomSpecialty have been populated");
        } catch (IOException ex) {
            throw new IllegalStateException("Problem while populating SymptomSpecialty with JSON predefined data", ex);
        }
    }

    public List<SymptomSpecialty> getSymptomSpecialties() {
        return this.symptomSpecialties;
    }
}
