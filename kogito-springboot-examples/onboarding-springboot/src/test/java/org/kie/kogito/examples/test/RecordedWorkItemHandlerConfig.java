package org.kie.kogito.examples.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RecordedWorkItemHandlerConfig extends DefaultWorkItemHandlerConfig {

    private RecordedOutputWorkItemHandler handler = new RecordedOutputWorkItemHandler();
    private final List<String> supportedHandlers = Arrays.asList("AssignDepartmentAndManager",
            "CalculatePaymentDate",
            "CalculateVacationDays",
            "CalculateTaxRate",
            "ValidateEmployee",
            "AssignIdAndEmail",
            "DecisionTask");

    @Override
    public KogitoWorkItemHandler forName(String name) {
        if (supportedHandlers.contains(name)) {
            return handler;
        }

        return super.forName(name);
    }

    @Override
    public Collection<String> names() {
        List<String> names = new ArrayList<>(supportedHandlers);
        names.addAll(super.names());
        return names;
    }
}
