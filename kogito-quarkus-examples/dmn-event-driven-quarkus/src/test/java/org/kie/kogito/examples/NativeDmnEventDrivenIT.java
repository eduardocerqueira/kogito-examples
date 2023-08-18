package org.kie.kogito.examples;

import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
@QuarkusTestResource(KafkaQuarkusTestResource.class)
public class NativeDmnEventDrivenIT extends DmnEventDrivenIT {

}
