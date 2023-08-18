package org.kie.kogito.app;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.drools.core.config.DefaultRuleEventListenerConfig;
import org.kie.kogito.examples.CustomRuleEventListener;

import io.micrometer.prometheus.PrometheusMeterRegistry;

@ApplicationScoped
public class RuleEventListenerConfig extends DefaultRuleEventListenerConfig {

    @Inject
    public RuleEventListenerConfig(PrometheusMeterRegistry prometheusMeterRegistry) {
        super(new CustomRuleEventListener(prometheusMeterRegistry));
    }
}
