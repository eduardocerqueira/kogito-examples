package org.kie.kogito.examples;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Inject
    MockService mockService;

    public Response processPayment(String orderId, String failService) {
        LOGGER.info("Process Payment for order {}", orderId);
        return mockService.execute(failService, PaymentService.class, true, orderId);
    }

    public Response processPayment(String orderId, String failService, String throwException) {
        LOGGER.info("Process Payment for order {}", orderId);
        return mockService.execute(failService, PaymentService.class, Boolean.parseBoolean(throwException), orderId);
    }

    public Response cancelPayment(String id) {
        LOGGER.info("Cancel Payment {}", id);
        return new Response(Response.Type.SUCCESS, id);
    }
}
