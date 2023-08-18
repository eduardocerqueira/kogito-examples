package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/queries")
@ApplicationScoped
public class QueryAnswerServiceResource {

    @Inject
    QueryRecordRepository repository;

    @GET
    public List<QueryRecord> get() {
        return repository.getAll();
    }
}
