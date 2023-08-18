package org.kie.kogito.examples.sw.temp.multiplication;

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
    @APIResponseSchema(value = OperationResource.Result.class, responseDescription = "MultiplicationResult", responseCode = "200")
    public Response doOperation(@NotNull MultiplicationOperation operation) {
        return Response.ok(new Result(operation.getLeftElement() * operation.getRightElement())).build();
    }

    @RegisterForReflection
    public static final class Result {

        float product;

        public Result() {
        }

        public Result(float product) {
            this.product = product;
        }

        public float getProduct() {
            return product;
        }

    }
}
