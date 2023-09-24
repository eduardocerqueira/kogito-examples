package org.acme.newsletter.subscription.service;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    /**
     * Confirms the email subscription
     */
    Subscription confirm(Subscription subscription);

    /**
     * Verify if the given email is already in the database
     */
    boolean checkEmail(String email);

    /**
     * Subscribes the given email
     */
    Subscription subscribe(Subscription subscription);

    /**
     * Deletes a subscription by id.
     */
    void delete(String id);

    /**
     * Fetch the subscription by email
     */
    Optional<Subscription> fetch(String email);

    List<Subscription> list(boolean verified);

}
