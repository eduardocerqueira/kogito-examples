package org.acme.newsletter.subscription.flow;

import org.acme.newsletter.subscription.service.Subscription;

public final class SubscriptionConstants {

    private SubscriptionConstants() {

    }

    public static final String EMAIL = "yeneffer@witch.com";
    public static final String ID = "12345";
    public static final String NAME = "Yeneffer";

    public static Subscription newSubscription() {
        final Subscription subscription = new Subscription(EMAIL);
        subscription.setId(ID);
        subscription.setName(NAME);
        subscription.setVerified(false);
        return subscription;
    }

}
