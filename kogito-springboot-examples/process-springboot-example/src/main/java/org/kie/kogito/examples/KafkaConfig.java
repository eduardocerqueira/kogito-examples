package org.kie.kogito.examples;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@Configuration
@ConditionalOnClass(KafkaTemplate.class)
public class KafkaConfig {
}
