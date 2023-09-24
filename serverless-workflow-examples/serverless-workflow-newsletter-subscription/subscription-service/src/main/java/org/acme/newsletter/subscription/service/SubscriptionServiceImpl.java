package org.acme.newsletter.subscription.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SubscriptionServiceImpl implements SubscriptionService {

    @Inject
    SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription confirm(Subscription subscription) {
        final Optional<Subscription> unconfirmedSub = subscriptionRepository.fetchByIdAndEmail(subscription.getId(), subscription.getEmail());
        if (unconfirmedSub.isEmpty()) {
            throw new SubscriptionException("Impossible to confirm subscription for email " + subscription.getEmail() + ". This email doesn't exist in the database.");
        }
        subscription.setVerified(true);
        subscriptionRepository.saveOrUpdate(subscription);
        return subscription;
    }

    @Override
    public boolean checkEmail(String email) {
        final Optional<Subscription> subscription = subscriptionRepository.fetchByEmail(email);
        return subscription.isPresent();
    }

    @Override
    public Subscription subscribe(Subscription subscription) {
        subscription.setVerified(false);
        subscriptionRepository.saveOrUpdate(subscription);
        return subscription;
    }

    @Override
    public void delete(String id) {
        subscriptionRepository.delete(id);
    }

    @Override
    public Optional<Subscription> fetch(String email) {
        return subscriptionRepository.fetchByEmail(email);
    }

    @Override
    public List<Subscription> list(boolean verified) {
        return subscriptionRepository.fetchAllByVerified(verified);
    }

}
