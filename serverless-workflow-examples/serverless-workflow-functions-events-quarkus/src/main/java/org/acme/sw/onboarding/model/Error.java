package org.acme.sw.onboarding.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Error {

    private String message;
    private Exception cause;

    public Error() {
    }

    public Error(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getCause() {
        return cause;
    }

    public void setCause(Exception cause) {
        this.cause = cause;
    }
}
