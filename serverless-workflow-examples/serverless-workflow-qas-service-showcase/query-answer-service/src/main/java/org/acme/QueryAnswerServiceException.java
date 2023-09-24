package org.acme;

public class QueryAnswerServiceException extends RuntimeException {

    public QueryAnswerServiceException(String message) {
        super(message);
    }

    public QueryAnswerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
