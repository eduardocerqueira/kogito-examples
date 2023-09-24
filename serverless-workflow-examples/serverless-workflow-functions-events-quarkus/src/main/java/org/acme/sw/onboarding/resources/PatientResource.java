package org.acme.sw.onboarding.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.sw.onboarding.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/onboarding/patient")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatientResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);
    /*
     * In a real world we would have a Database.
     * See: https://quarkus.io/guides/hibernate-orm-panache
     */
    private final List<Patient> patients;

    public PatientResource() {
        this.patients = new ArrayList<>();
    }

    @POST
    public Response storeNewPatient(@NotNull final Patient patient) {
        LOGGER.debug("Received patient to store in the internal in memory database: {}", patient);
        patient.setId(UUID.randomUUID().toString());
        patients.add(patient);
        LOGGER.debug("Patient has been stored in the internal memory: {}", patient);
        return Response.ok(patient).build();
    }

    @GET
    public List<Patient> getPatients() {
        return this.patients;
    }

    @GET
    @Path("/{id}")
    public Optional<Patient> getPatient(@NotEmpty @PathParam("id") final String patientId) {
        return this.patients.stream().filter(p -> patientId.equals(p.getId())).findFirst();
    }
}
