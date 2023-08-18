package org.kie.kogito.hr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.auth.SecurityPolicy;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.testcontainers.quarkus.InfinispanQuarkusTestResource;
import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@QuarkusTestResource(value = InfinispanQuarkusTestResource.class)
@QuarkusTestResource(value = KafkaQuarkusTestResource.class)
public class HiringProcessIT {

    @Named("hiring")
    @Inject
    Process<? extends Model> hiringProcess;

    @Test
    public void testApprovalProcess() {

        assertNotNull(hiringProcess);

        Model m = hiringProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("candidate", new Candidate("jdoe", "jdoe@example.com", 30000, "Java, Kogito"));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = hiringProcess.createInstance(m);
        processInstance.start();
        assertEquals(org.kie.api.runtime.process.ProcessInstance.STATE_ACTIVE, processInstance.status());

        SecurityPolicy policy = SecurityPolicy.of(IdentityProviders.of("jdoe", Arrays.asList("HR", "IT")));

        processInstance.workItems(policy);

        List<WorkItem> workItems = processInstance.workItems(policy);
        assertEquals(1, workItems.size());
        Map<String, Object> results = new HashMap<>();
        results.put("approve", true);
        processInstance.completeWorkItem(workItems.get(0).getId(), results, policy);

        processInstance.workItems(policy);

        workItems = processInstance.workItems(policy);
        assertEquals(1, workItems.size());

        results.put("approve", false);
        processInstance.completeWorkItem(workItems.get(0).getId(), results, policy);
        assertEquals(org.kie.api.runtime.process.ProcessInstance.STATE_COMPLETED, processInstance.status());

        Model result = (Model) processInstance.variables();
        assertEquals(3, result.toMap().size());
        assertEquals(true, result.toMap().get("hr_approval"));
        assertEquals(false, result.toMap().get("it_approval"));
    }
}
