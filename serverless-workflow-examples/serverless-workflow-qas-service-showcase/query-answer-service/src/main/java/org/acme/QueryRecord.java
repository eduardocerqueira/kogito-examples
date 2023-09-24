package org.acme;

import java.time.ZonedDateTime;

public class QueryRecord {

    public static final String PENDING = "PENDING";
    public static final String RESOLVED = "RESOLVED";
    public static final String ERROR = "ERROR";

    private String processInstanceId;
    private ZonedDateTime created;
    private String status;
    private String query;
    private String answer;
    private ZonedDateTime lastModified;

    public QueryRecord() {
    }

    public QueryRecord(String processInstanceId, ZonedDateTime created, String status, String query, String answer, ZonedDateTime lastModified) {
        this.processInstanceId = processInstanceId;
        this.created = created;
        this.status = status;
        this.query = query;
        this.answer = answer;
        this.lastModified = lastModified;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String id) {
        this.processInstanceId = id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "QueryRecord{" +
                "processInstanceId='" + processInstanceId + '\'' +
                ", created=" + created +
                ", status='" + status + '\'' +
                ", query='" + query + '\'' +
                ", answer='" + answer + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}
