package org.kie.kogito.examples;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.kie.kogito.examples.SwitchStateTimeoutsClient.CONFIG_KEY;

@Path("/")
@RegisterRestClient(configKey = CONFIG_KEY)
public interface SwitchStateTimeoutsClient extends WorkflowClient {

    /**
     * SW id that was configured in the switch_state_timeouts.yaml.
     */
    String ID = "switchstatetimeouts";
    String URI = "/" + ID;
    String CONFIG_KEY = "switch_state_timeouts";

    @POST
    @Path(URI)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response post(@Context HttpHeaders httpHeaders, @QueryParam("businessKey") @DefaultValue("") String businessKey, String input);

    @GET
    @Path(URI)
    @Produces(MediaType.APPLICATION_JSON)
    Response get();

}

