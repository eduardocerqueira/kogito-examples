package org.acme.numbers;

import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/numbers")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class NumbersResource {

    private ObjectMapper objectMapper;
    private Random rand;
    private static final Logger logger = LoggerFactory.getLogger(NumbersResource.class);

    @PostConstruct
    void init() {
        objectMapper = new ObjectMapper();
        rand = new Random();
    }

    @GET
    @Path("random")
    public Response getRandom(@QueryParam("bound") @DefaultValue("10") int bound) {
        return fromNumber("randomNumber", rand.nextInt(bound));
    }

    @POST
    @Path("{number}/multiplyByAndSum")
    public Response multiplyByAndSum(@PathParam("number") int multiplier, Numbers numbers) {
        Object extra = numbers.getAdditionalData();
        if (extra != null) {
            logger.info("Additional data {}", extra);
        }
        return fromNumber("sum", numbers.getNumbers().stream().map(n -> n.intValue() * multiplier).collect(Collectors.summingInt(Integer::intValue)));
    }

    private Response fromNumber(String name, int number) {
        return Response.ok().entity(objectMapper.createObjectNode().put(name, number)).build();
    }

}
