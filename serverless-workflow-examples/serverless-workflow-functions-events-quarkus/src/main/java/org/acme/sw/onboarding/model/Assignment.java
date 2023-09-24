package org.acme.sw.onboarding.model;

import java.util.ArrayList;
import java.util.List;

public class Assignment {

    private List<Patient> patients;

    public Assignment() {
        this.patients = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "patients=" + patients +
                '}';
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
