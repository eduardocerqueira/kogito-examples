package org.kogito.serverless.examples.services;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.kogito.serverless.examples.input.Country;

@ApplicationScoped
public class PopulationService {

    private Map<String, String> populations = new HashMap<>();

    public PopulationService() {
        populations.put("Brazil", "211,000,000");
        populations.put("USA", "6,000,000");
        populations.put("Serbia", "6,945,000");
        populations.put("Germany", "83,020,000");
        populations.put("N/A", "N/A");
    }

    public Country getPopulation(Country country) {
        country.setPopulation(populations.get(country.getName()));
        return country;
    }
}
