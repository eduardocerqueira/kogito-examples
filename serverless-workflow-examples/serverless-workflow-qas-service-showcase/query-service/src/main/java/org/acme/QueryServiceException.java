package org.acme;

public class QueryServiceException extends RuntimeException {

    public QueryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
