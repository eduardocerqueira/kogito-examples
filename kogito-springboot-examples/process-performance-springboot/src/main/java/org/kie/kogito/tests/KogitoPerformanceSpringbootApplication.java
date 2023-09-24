package org.kie.kogito.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.kie.kogito.**", "org.kie.kogito.tests.**", "org.drools.project.model.**" })
public class KogitoPerformanceSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KogitoPerformanceSpringbootApplication.class, args);
    }

}
