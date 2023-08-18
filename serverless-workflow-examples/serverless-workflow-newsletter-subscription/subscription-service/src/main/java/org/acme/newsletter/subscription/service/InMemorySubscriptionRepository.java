package org.acme.newsletter.subscription.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.arc.DefaultBean;

/**
 * This default implementation is used when the persistence is not enabled.
 */
@DefaultBean
@ApplicationScoped
public class InMemorySubscriptionRepository implements SubscriptionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemorySubscriptionRepository.class);

    private final Map<String, Subscription> subscriptionMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        LOGGER.info("The {} repository will be used. " +
                            "You can build the application with the persistence profile to use a PostgreSQL database. " +
                            "Read the project documentation for more information.", InMemorySubscriptionRepository.class.getName());
    }

    @Override
    public Optional<Subscription> fetchByEmail(String email) {
        return Optional.ofNullable(subscriptionMap.getOrDefault(email, null));
    }

    public Optional<Subscription> fetchByIdAndEmail(String id, String email) {
        final Optional<Subscription> subscription = Optional.ofNullable(subscriptionMap.getOrDefault(email, null));
        if (subscription.isPresent() && !id.equals(subscription.get().getId())) {
            return Optional.empty();
        }
        return subscription;
    }

    @Override
    public void saveOrUpdate(Subscription subscription) {
        this.subscriptionMap.put(subscription.getEmail(), subscription);
    }

    @Override
    public void delete(String id) {
        this.subscriptionMap.entrySet().stream()
                .filter(entry -> entry.getValue().getId().equals(id))
                .findFirst()
                .ifPresent(entry -> subscriptionMap.remove(entry.getKey()));
    }

    @Override
    public List<Subscription> fetchAllByVerified(boolean verified) {
        return subscriptionMap.values().stream().filter(s -> s.isVerified() == verified).collect(Collectors.toUnmodifiableList());
    }
}
