package org.kogito.serverless.examples;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.kie.kogito.addon.quarkus.messaging.common.ChannelFormat;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.avro.AvroCloudEventUnmarshallerFactory;
import org.kie.kogito.event.avro.AvroIO;

@ApplicationScoped
public class AvroMarshallerProducer {

    private AvroIO avroIO;

    @PostConstruct
    void init() throws IOException {
        avroIO = new AvroIO();
    }

    @Produces
    @Named("avro")
    @ChannelFormat
    public CloudEventUnmarshallerFactory<byte[]> getAvroCloudEventUnmarshallerFactory() {
        return new AvroCloudEventUnmarshallerFactory(avroIO);
    }

    @Produces
    AvroIO getAvroIO() {
        return avroIO;
    }

}