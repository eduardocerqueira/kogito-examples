package org.acme.wih;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;

@ApplicationScoped
public class CustomWorkItemHandlerConfig extends DefaultWorkItemHandlerConfig {

    {
        register("CustomTask", new CustomTaskWorkItemHandler());
    }
}
