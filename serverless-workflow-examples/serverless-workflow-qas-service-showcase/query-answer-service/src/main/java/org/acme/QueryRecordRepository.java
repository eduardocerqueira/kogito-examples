package org.acme;

import java.util.List;

public interface QueryRecordRepository {

    void saveOrUpdate(QueryRecord queryRecord);

    QueryRecord get(String id);

    List<QueryRecord> getAll();

}
