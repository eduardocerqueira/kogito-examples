package org.kie.kogito.performance.client;

import org.kie.kogito.performance.client.RequestDispatcherFactory.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainRunner {

    private static final Logger logger = LoggerFactory.getLogger(MainRunner.class);

    public static void main(String[] args) {
        try (RequestDispatcher dispatcher = RequestDispatcherFactory.getDispatcher(RequestType.KAFKA, "test")) {
            new RequestDispatcherRunner(dispatcher, 100, 10).call();
        } catch (Exception ex) {
            logger.error("Execution error ", ex);
            System.exit(-1);
        }
        System.exit(0);
    }
}
