package org.acme.newsletter.subscription.service;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    Optional<Subscription> fetchByEmail(String email);

    Optional<Subscription> fetchByIdAndEmail(String id, String email);

    List<Subscription> fetchAllByVerified(boolean verified);

    void saveOrUpdate(Subscription subscription);

    void delete(String id);

    default boolean isValidId(String id) {
        return id != null && !id.isBlank();
    }

}
