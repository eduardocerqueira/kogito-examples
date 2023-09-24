package org.kie.kogito.examples;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public Response success(String orderId) {
        LOGGER.info("Order Success for order {}", orderId);
        return Response.success(orderId);
    }

    public Response failure(String orderId) {
        LOGGER.info("Order Failed for order {}", orderId);
        return Response.error(orderId);
    }
}
