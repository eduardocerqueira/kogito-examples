package org.kie.kogito.dmn.springboot.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.kie.kogito.dmn.**", "org.kie.kogito.**", "org.kie.kogito.tracing.**", "http**", "org.kie.kogito.monitoring.*" })
public class KogitoSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KogitoSpringbootApplication.class, args);
    }
}