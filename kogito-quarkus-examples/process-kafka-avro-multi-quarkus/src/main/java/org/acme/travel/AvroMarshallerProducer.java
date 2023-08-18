package org.acme.travel;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.kie.kogito.event.EventMarshaller;
import org.kie.kogito.event.EventUnmarshaller;
import org.kie.kogito.event.avro.AvroEventMarshaller;
import org.kie.kogito.event.avro.AvroEventUnmarshaller;
import org.kie.kogito.event.avro.AvroIO;

@ApplicationScoped
public class AvroMarshallerProducer {

    private AvroIO avroUtils;

    @PostConstruct
    void init() throws IOException {
        avroUtils = new AvroIO();
    }

    @Produces
    EventMarshaller<byte[]> getAvroMarshaller() {
        return new AvroEventMarshaller(avroUtils);
    }

    @Produces
    EventUnmarshaller<byte[]> getAvroUnmarshaller() {
        return new AvroEventUnmarshaller(avroUtils);
    }

    // publish as bean for testing
    @Produces
    AvroIO getAvroUtils() {
        return avroUtils;
    }

}
