package org.kie.kogito.performance.client;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.kie.kogito.event.process.ProcessDataEvent;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaDispatcher implements RequestDispatcher {

    private class ObjectCloudEvent extends ProcessDataEvent<Object> {
        public ObjectCloudEvent(String trigger, Object data) {
            super(trigger, "java_client", data, null, null, null, null, null, null, null, null, null, null);
        }
    }

    private String trigger;
    private ObjectMapper objectMapper;
    private KafkaProducer<byte[], byte[]> kafkaProducer;

    public KafkaDispatcher(String trigger) {
        this.trigger = trigger;
        this.objectMapper = ObjectMapperFactory.get();
        Map<String, Object> properties = Collections.singletonMap(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        kafkaProducer = new KafkaProducer<>(properties, new ByteArraySerializer(), new ByteArraySerializer());
    }

    @Override
    public void dispatch(long delay, Consumer<Throwable> consumer) {
        try {
            kafkaProducer.send(new ProducerRecord<>("test", objectMapper.writeValueAsBytes(new ObjectCloudEvent(trigger, delay))), (r, e) -> {
                if (e != null) {
                    consumer.accept(e);
                }
            });
        } catch (JsonProcessingException e) {
            consumer.accept(e);
        }
    }

    @Override
    public void close() {
        kafkaProducer.close();
    }

}
