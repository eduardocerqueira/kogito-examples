package org.kie.kogito.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.cache.CacheMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.kubernetes.fabric8.discovery.KubernetesCatalogWatchAutoConfiguration;
import org.springframework.cloud.kubernetes.fabric8.discovery.KubernetesDiscoveryClientAutoConfiguration;

// Disabling the cache metrics for now, see: https://github.com/infinispan/infinispan-spring-boot/issues/168
@SpringBootApplication(scanBasePackages = { "org.kie.kogito.**" },
        exclude = { CacheMetricsAutoConfiguration.class,
                KubernetesDiscoveryClientAutoConfiguration.class,
                KubernetesCatalogWatchAutoConfiguration.class })
public class KogitoOnboardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KogitoOnboardingApplication.class, args);
    }
}
