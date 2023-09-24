package org.kie.kogito.examples.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.Model;
import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.auth.SecurityPolicy;
import org.kie.kogito.examples.DemoApplication;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.testcontainers.springboot.InfinispanSpringBootTestResource;
import org.kie.kogito.testcontainers.springboot.KafkaSpringBootTestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@ContextConfiguration(initializers = { InfinispanSpringBootTestResource.Conditional.class, KafkaSpringBootTestResource.Conditional.class })
public class PersonProcessIT {

    @Autowired
    @Qualifier("persons")
    Process<? extends Model> personProcess;

    private SecurityPolicy policy = SecurityPolicy.of(IdentityProviders.of("admin", Collections.singletonList("managers")));

    @Test
    public void testPersonsProcessIsAdult() {
        Model m = personProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person",
                new Person("John Doe",
                        20));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = personProcess.createInstance(m);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_COMPLETED,
                processInstance.status());
        Model result = (Model) processInstance.variables();
        assertEquals(1,
                result.toMap().size());
        assertTrue(((Person) result.toMap().get("person")).isAdult());
    }

    @Test
    public void testPersonsProcessIsChild() {
        Model m = personProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person",
                new Person("Jenny Quark",
                        14));
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = personProcess.createInstance(m);
        processInstance.start();

        assertEquals(ProcessInstance.STATE_ACTIVE,
                processInstance.status());
        Model result = (Model) processInstance.variables();
        assertEquals(1,
                result.toMap().size());
        assertFalse(((Person) result.toMap().get("person")).isAdult());

        List<WorkItem> workItems = processInstance.workItems(policy);
        assertEquals(1,
                workItems.size());

        processInstance.completeWorkItem(workItems.get(0).getId(),
                null,
                policy);

        assertEquals(ProcessInstance.STATE_COMPLETED,
                processInstance.status());
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
