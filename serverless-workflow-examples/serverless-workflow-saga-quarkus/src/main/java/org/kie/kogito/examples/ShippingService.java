package org.kie.kogito.examples;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ShippingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingService.class);

    @Inject
    MockService mockService;

    public Response scheduleShipping(String orderId, String failService) {
        LOGGER.info("Schedule Shipping for order {}", orderId);
        return mockService.execute(failService, ShippingService.class, true, orderId);
    }

    public Response scheduleShipping(String orderId, String failService, String throwException) {
        LOGGER.info("Schedule Shipping for order {}", orderId);
        return mockService.execute(failService, ShippingService.class, Boolean.parseBoolean(throwException), orderId);
    }

    public Response cancelShipping(String id) {
        LOGGER.info("Cancel Shipping {}", id);
        return new Response(Response.Type.SUCCESS, id);
    }
}
