package org.acme.sw.onboarding.queries;

import java.util.List;

import org.acme.sw.onboarding.model.Doctor;
import org.acme.sw.onboarding.model.Patient;
import org.acme.sw.onboarding.model.SymptomSpecialty;
import org.acme.sw.onboarding.services.DoctorService;
import org.acme.sw.onboarding.services.SymptomSpecialtyService;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignmentUnit implements RuleUnitData {

    private DataStore<Patient> patients;
    private DataStore<Doctor> doctors;
    private DataStore<SymptomSpecialty> symptomSpecialties;

    public AssignmentUnit() {
        this.patients = DataSource.createStore();
        this.doctors = DataSource.createStore();
        this.symptomSpecialties = DataSource.createStore();
        this.populate();
    }

    private void populate() {
        DoctorService.get().getDoctors().forEach(this.doctors::add);
        SymptomSpecialtyService.get().getSymptomSpecialties().forEach(this.symptomSpecialties::add);
    }

    public DataStore<Patient> getPatients() {
        return patients;
    }

    public void setPatients(DataStore<Patient> patients) {
        this.patients = patients;
    }

    public void addPatients(final List<Patient> patientList) {
        for (Patient p : patientList) {
            this.patients.add(p);
        }
    }

    @JsonIgnore
    public DataStore<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(DataStore<Doctor> doctors) {
        this.doctors = doctors;
    }

    @JsonIgnore
    public DataStore<SymptomSpecialty> getSymptomSpecialties() {
        return symptomSpecialties;
    }

    public void setSymptomSpecialties(DataStore<SymptomSpecialty> symptomSpecialties) {
        this.symptomSpecialties = symptomSpecialties;
    }
}
