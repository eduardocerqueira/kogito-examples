package org.acme.travels.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/flightservice")
public class FlightBookingResource {

    @Inject
    FlightBookingService service;

    @Path("/shouldtimeout")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean getShouldTimeout() {
        return service.getShouldTimeout();
    }

    @Path("/shouldtimeout")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean toggleTimeout() {
        service.setShouldTimeout(!service.getShouldTimeout());
        return getShouldTimeout();
    }
}
