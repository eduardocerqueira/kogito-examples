package org.acme.deals;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.Startup;
import io.smallrye.common.annotation.Identifier;

import static java.util.Collections.singleton;

@ApplicationScoped
@Startup
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final String KOGITO_PROCESS = "kogito.process";

    @Inject
    @Identifier("default-kafka-broker")
    Map<String, Object> kafkaConfig;

    @PostConstruct
    public void init() throws Exception {
        try (AdminClient client = AdminClient.create(kafkaConfig)) {
            Set<String> topics = client.listTopics().names().get(1, TimeUnit.MINUTES);

            if (!topics.contains(KOGITO_PROCESS)) {
                client.createTopics(singleton(new NewTopic(KOGITO_PROCESS, 1, (short) 1))).all().get(1, TimeUnit.MINUTES);
                LOGGER.info("Created kogito.process topic in Kafka");
            }
        }
    }
}
