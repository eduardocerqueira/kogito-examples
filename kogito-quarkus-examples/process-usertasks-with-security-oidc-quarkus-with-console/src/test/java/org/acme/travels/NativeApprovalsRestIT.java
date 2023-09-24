package org.acme.travels;

import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;
import org.kie.kogito.testcontainers.quarkus.KeycloakQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
@QuarkusTestResource(KeycloakQuarkusTestResource.class)
@QuarkusTestResource(KafkaQuarkusTestResource.class)
public class NativeApprovalsRestIT extends ApprovalsRestIT {
    // run the same tests only against native image
}