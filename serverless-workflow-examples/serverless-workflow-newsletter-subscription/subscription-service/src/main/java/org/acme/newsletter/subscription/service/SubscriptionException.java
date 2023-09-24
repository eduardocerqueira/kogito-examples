package org.acme.newsletter.subscription.service;

public class SubscriptionException extends RuntimeException {

    public SubscriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionException(String message) {
        super(message);
    }

}
