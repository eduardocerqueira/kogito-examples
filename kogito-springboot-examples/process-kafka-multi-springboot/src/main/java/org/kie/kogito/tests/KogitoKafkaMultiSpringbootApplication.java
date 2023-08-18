package org.kie.kogito.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.kie.kogito.**", "org.kie.kogito.tests.**", "org.drools.project.model.**" })
public class KogitoKafkaMultiSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KogitoKafkaMultiSpringbootApplication.class, args);
    }

}
