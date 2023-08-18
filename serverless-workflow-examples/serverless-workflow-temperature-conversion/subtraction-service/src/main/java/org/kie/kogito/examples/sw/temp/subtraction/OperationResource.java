package org.kie.kogito.examples.sw.temp.subtraction;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;

import io.quarkus.runtime.annotations.RegisterForReflection;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource {

    @POST
    @APIResponseSchema(value = OperationResource.Result.class, responseDescription = "SubtractionResult", responseCode = "200")
    public Response doOperation(@NotNull SubtractionOperation operation) {
        return Response.ok(new Result(operation.getLeftElement() - operation.getRightElement())).build();
    }

    @RegisterForReflection
    public static final class Result {

        float difference;

        public Result() {
        }

        public Result(final float difference) {
            this.difference = difference;
        }

        public float getDifference() {
            return difference;
        }
    }
}
