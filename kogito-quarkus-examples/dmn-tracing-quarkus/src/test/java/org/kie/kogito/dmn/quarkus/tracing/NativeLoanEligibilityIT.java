package org.kie.kogito.dmn.quarkus.tracing;

import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
@QuarkusTestResource(KafkaQuarkusTestResource.class)
public class NativeLoanEligibilityIT extends LoanEligibilityIT {

    // Execute the same tests but in native mode.
}
