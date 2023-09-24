package org.acme;

import java.util.List;

public interface QueryRequestRepository {

    void saveOrUpdate(QueryRequest queryRequest);

    void delete(String id);

    List<QueryRequest> getAll();
}
