package org.kie.kogito.examples;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.auth.SecurityPolicy;
import org.kie.kogito.examples.demo.Person;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.testcontainers.quarkus.InfinispanQuarkusTestResource;
import org.kie.kogito.testcontainers.quarkus.KafkaQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(value = InfinispanQuarkusTestResource.Conditional.class)
@QuarkusTestResource(value = KafkaQuarkusTestResource.Conditional.class)
public class PersonProcessIT {

    @Inject
    @Named("persons")
    Process<? extends Model> personProcess;

    private SecurityPolicy policy = SecurityPolicy.of(IdentityProviders.of("admin", Collections.singletonList("managers")));

    @Test
    public void testAdult() {

        Model m = personProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person", new Person("John Doe", 20));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = personProcess.createInstance(m);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
        Model result = (Model) processInstance.variables();
        assertEquals(1, result.toMap().size());
        assertTrue(((Person) result.toMap().get("person")).isAdult());
    }

    @Test
    public void testChild() {
        Model m = personProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person", new Person("Jenny Quark", 14));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = personProcess.createInstance(m);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_ACTIVE, processInstance.status());
        Model result = (Model) processInstance.variables();
        assertEquals(1, result.toMap().size());
        assertFalse(((Person) result.toMap().get("person")).isAdult());

        List<WorkItem> workItems = processInstance.workItems(policy);
        assertEquals(1, workItems.size());

        processInstance.completeWorkItem(workItems.get(0).getId(), null, policy);

        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
    }

    @Test
    public void testChildWithSecurityPolicy() {
        Model m = personProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person", new Person("Jenny Quark", 14));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = personProcess.createInstance(m);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_ACTIVE, processInstance.status());
        Model result = (Model) processInstance.variables();
        assertEquals(1, result.toMap().size());
        assertFalse(((Person) result.toMap().get("person")).isAdult());

        List<WorkItem> workItems = processInstance.workItems(policy);
        assertEquals(1, workItems.size());

        processInstance.completeWorkItem(workItems.get(0).getId(), null, policy);

        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.status());
    }

    @Test
    public void testChildWithSecurityPolicyNotAuthorized() {
        Model m = personProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person", new Person("Jenny Quark", 14));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = personProcess.createInstance(m);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_ACTIVE, processInstance.status());
        Model result = (Model) processInstance.variables();
        assertEquals(1, result.toMap().size());
        assertFalse(((Person) result.toMap().get("person")).isAdult());

        SecurityPolicy johnPolicy = SecurityPolicy.of(IdentityProviders.of("john"));

        List<WorkItem> workItems = processInstance.workItems(johnPolicy);
        assertEquals(0, workItems.size());

        processInstance.abort();

        assertEquals(ProcessInstance.STATE_ABORTED, processInstance.status());
    }
}
