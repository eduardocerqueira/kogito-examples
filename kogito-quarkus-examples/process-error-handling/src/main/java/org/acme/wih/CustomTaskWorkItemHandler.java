package org.acme.wih;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.ProcessWorkItemHandlerException;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTaskWorkItemHandler implements KogitoWorkItemHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomTaskWorkItemHandler.class);

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        LOG.debug("start");
        LOG.debug("Passed parameters:");

        // Printing task’s parameters, it will also print
        // our value we pass to the task from the process
        for (String parameter : workItem.getParameters().keySet()) {
            LOG.debug(parameter + " = " + workItem.getParameters().get(parameter));
        }

        String input = (String) workItem.getParameter("Input");

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("Result", "Hello " + input);

        if (input.matches("(RETRY|COMPLETE|RETHROW)")) {
            handleError(input);
        } else if (input.contentEquals("ABORT")) {
            manager.abortWorkItem(workItem.getStringId());
        } else {
            // Don’t forget to finish the work item otherwise the process
            // will be active infinitely and never will pass the flow
            // to the next node.
            manager.completeWorkItem(workItem.getStringId(), results);
        }

        LOG.debug("end");
    }

    private void handleError(String strategy) {
        throw new ProcessWorkItemHandlerException("error_handling",
                ProcessWorkItemHandlerException.HandlingStrategy.valueOf(strategy),
                new IllegalStateException(strategy + " strategy test"));
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        LOG.debug("ABORT!");
        manager.abortWorkItem(workItem.getStringId());
    }
}