package org.kie.kogito.app;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.process.impl.DefaultProcessEventListenerConfig;

import io.micrometer.prometheus.PrometheusMeterRegistry;

@ApplicationScoped
public class ProcessEventListenerConfig extends DefaultProcessEventListenerConfig {

    private VisaApplicationPrometheusProcessEventListener listener;

    @Inject
    ConfigBean configBean;

    @Inject
    PrometheusMeterRegistry prometheusMeterRegistry;

    public ProcessEventListenerConfig() {
        super();
    }

    @PostConstruct
    public void setup() {
        this.listener = new VisaApplicationPrometheusProcessEventListener("acme-travels",
                configBean.getGav().orElse(KogitoGAV.EMPTY_GAV), prometheusMeterRegistry);
        register(this.listener);
    }

    @PreDestroy
    public void close() {
        this.listener.cleanup();
    }
}
