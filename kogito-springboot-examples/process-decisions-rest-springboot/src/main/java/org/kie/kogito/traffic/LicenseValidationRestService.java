package org.kie.kogito.traffic;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LicenseValidationRestService {

    private final URI uri;

    public LicenseValidationRestService() {
        uri = null;
    }

    @Autowired
    public LicenseValidationRestService(@Value("${license.validation.url}") String url) {
        uri = UriComponentsBuilder.fromUriString(url)
                .path("/validation/first")
                .build()
                .toUri();
    }

    public Driver evaluate(Driver driver) {
        return new RestTemplateBuilder()
                .build()
                .postForObject(uri, Collections.singletonMap("driver", driver), Driver.class);
    }
}