package org.kie.kogito.performance.client;

public class RequestDispatcherFactory {

    private RequestDispatcherFactory() {
    }

    public enum RequestType {
        REST,
        REST_ASYNC,
        KAFKA
    }

    public static RequestDispatcher getDispatcher(RequestType type, String processId) {

        switch (type) {
            case REST:
                return new SyncRestDispatcher(processId);
            case REST_ASYNC:
                return new AsyncRestDispatcher(processId);
            case KAFKA:
                return new KafkaDispatcher(processId);
            default:
                throw new UnsupportedOperationException("Unknown type " + type);
        }

    }

}
