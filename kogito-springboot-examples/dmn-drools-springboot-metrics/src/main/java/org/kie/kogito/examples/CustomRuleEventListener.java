package org.kie.kogito.examples;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.event.KieRuntimeEvent;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.prometheus.PrometheusMeterRegistry;

public class CustomRuleEventListener extends DefaultAgendaEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomRuleEventListener.class);

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    public CustomRuleEventListener(PrometheusMeterRegistry prometheusMeterRegistry) {
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }

    @Override
    public void matchCreated(MatchCreatedEvent event) {
        super.matchCreated(event);
        registerEvent(event);
    }

    @Override
    public void matchCancelled(MatchCancelledEvent event) {
        super.matchCancelled(event);
        registerEvent(event);
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        super.beforeMatchFired(event);
        registerEvent(event);
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        super.afterMatchFired(event);
        registerEvent(event);
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        super.agendaGroupPopped(event);
        registerEvent(event);
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        super.agendaGroupPushed(event);
        registerEvent(event);
    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        super.beforeRuleFlowGroupActivated(event);
        registerEvent(event);
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        super.afterRuleFlowGroupActivated(event);
        registerEvent(event);
    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        super.beforeRuleFlowGroupDeactivated(event);
        registerEvent(event);
    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        super.afterRuleFlowGroupDeactivated(event);
        registerEvent(event);
    }

    private void registerEvent(KieRuntimeEvent event) {
        logger.debug(event.getClass().getSimpleName());
        prometheusMeterRegistry.counter("org.kie.kogito.examples.customruleeventlistener", "event",
                event.getClass().getSimpleName().toLowerCase()).increment();
    }
}
