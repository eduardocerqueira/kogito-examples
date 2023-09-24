package org.kogito.examples.sw.github.workflow;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.fasterxml.jackson.databind.JsonNode;

@Path("/repo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface GitHubClient {

    @POST
    @Path("/{user}/{name}/pr/{number}/labels")
    Response addLabels(@PathParam("user") String user,
            @PathParam("name") String repoName,
            @PathParam("number") Integer prNumber,
            List<String> labels);

    @POST
    @Path("/{user}/{name}/pr/{number}/reviewers")
    Response addReviewers(@PathParam("user") String user,
            @PathParam("name") String repoName,
            @PathParam("number") Integer prNumber,
            List<String> reviewers);

    @GET
    @Path("/{user}/{name}/pr/{number}/files")
    JsonNode fetchFiles(@PathParam("user") String user,
            @PathParam("name") String repoName,
            @PathParam("number") Integer prNumber);
}
