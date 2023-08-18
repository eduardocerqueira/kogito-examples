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

@Path("/numbers")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class NumbersResource {

    private Random rand;

    @PostConstruct
    void init() {
        rand = new Random();
    }

    @GET
    @Path("random")
    public Response getRandom(@QueryParam("bound") @DefaultValue("10") int bound) {
        return fromNumber(rand.nextInt(bound));
    }

    @POST
    @Path("{number}/multiplyByAndSum")
    public Response multiplyByAndSum(@PathParam("number") int multiplier, Numbers numbers) {
        return fromNumber(numbers.getNumbers().stream().map(n -> n.intValue() * multiplier).collect(Collectors.summingInt(Integer::intValue)));
    }

    private Response fromNumber(int number) {
        return Response.ok().entity(number).build();
    }

}
