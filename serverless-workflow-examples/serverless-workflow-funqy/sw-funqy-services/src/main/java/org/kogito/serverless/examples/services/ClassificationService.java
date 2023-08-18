package org.kogito.serverless.examples.services;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.kogito.serverless.examples.input.Country;

@ApplicationScoped
public class ClassificationService {

    private Map<String, String> classifications = new HashMap<>();

    public ClassificationService() {
        classifications.put("Brazil", "Large");
        classifications.put("USA", "Large");
        classifications.put("Serbia", "Small");
        classifications.put("Germany", "Medium");
        classifications.put("N/A", "N/A");
    }

    public Country getClassification(Country country) {
        country.setClassifier(classifications.get(country.getName()));
        return country;
    }
}
