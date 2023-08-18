package org.kie.kogito.examples;

public class EventInput {

    private String uri;
    private String processInstanceId;

    public EventInput() {
    }

    public String getUri() {
        return uri;
    }
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public String toString() {
        return "EventInput{" +
                "uri='" + uri + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                '}';
    }
}
