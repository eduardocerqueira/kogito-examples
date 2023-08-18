package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.arc.DefaultBean;

/**
 * This default implementation is used when the persistence is not enabled.
 */
@DefaultBean
@ApplicationScoped
public class InMemoryQueryRequestRepository implements QueryRequestRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryQueryRequestRepository.class);

    private final Map<String, QueryRequest> queryRequestMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        LOGGER.info("The {} repository will be used. " +
                "You can build the application with the persistence profile to use a PostgreSQL database. " +
                "Read the project documentation for more information.", InMemoryQueryRequestRepository.class.getName());
    }

    @Override
    public void saveOrUpdate(QueryRequest queryRequest) {
        queryRequestMap.put(queryRequest.getProcessInstanceId(), queryRequest);
    }

    @Override
    public void delete(String id) {
        queryRequestMap.remove(id);
    }

    @Override
    public List<QueryRequest> getAll() {
        return new ArrayList<>(queryRequestMap.values());
    }
}
