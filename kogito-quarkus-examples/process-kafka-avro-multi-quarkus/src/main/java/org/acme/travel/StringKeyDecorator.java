package org.acme.travel;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.kie.kogito.addon.quarkus.common.reactive.messaging.MessageDecorator;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;

@ApplicationScoped
public class StringKeyDecorator implements MessageDecorator {

    @Override
    public <T> Message<T> decorate(Message<T> message) {
        return message.addMetadata(OutgoingKafkaRecordMetadata.<String> builder().withKey("Real Betis Balompie").build());
    }
}
