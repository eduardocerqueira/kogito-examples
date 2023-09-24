package org.acme;

public class QueryRequest {

    private String processInstanceId;
    private String query;

    public QueryRequest() {
    }

    public QueryRequest(String processInstanceId, String query) {
        this.processInstanceId = processInstanceId;
        this.query = query;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getQuery() {
        return query;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "QueryRequest{" +
                "processInstanceId='" + processInstanceId + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
