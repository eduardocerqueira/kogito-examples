package org.kie.kogito.examples;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MockService {

    private static Logger LOGGER = LoggerFactory.getLogger(MockService.class);

    public Response execute(String failClass, Class clazz, boolean throwException, String resourceId) {
        boolean fail = Optional.ofNullable(clazz)
                .map(Class::getSimpleName)
                .map(n -> Objects.equals(failClass, n))
                .orElse(false);
        if (fail) {
            LOGGER.error("Error in {} for {}", failClass, resourceId);
        }
        if (fail && throwException) {
            throw new ServiceException("Error executing " + failClass + " for " + resourceId);
        }
        return new Response(fail ? Response.Type.ERROR : Response.Type.SUCCESS,
                UUID.randomUUID().toString());
    }
}
