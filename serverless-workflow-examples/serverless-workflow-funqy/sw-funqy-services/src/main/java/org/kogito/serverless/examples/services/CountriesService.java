package org.kogito.serverless.examples.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.kogito.serverless.examples.input.Country;

@ApplicationScoped
public class CountriesService {

    private List<Country> availableCountries = new ArrayList<>();
    private Country errorCountry = new Country("N/A", "N/A", "N/A");

    public CountriesService() {
        availableCountries.add(
                new Country("Brazil", "Brasilia", "South America"));
        availableCountries.add(
                new Country("USA", "Washington, D.C.", "North America"));
        availableCountries.add(
                new Country("Serbia", "Belgrade", "Europe"));
        availableCountries.add(
                new Country("Germany", "Berlin", "Europe"));
    }

    public Country getCountry(String countryName) {
        return availableCountries.stream()
                .filter(c -> c.getName().equals(countryName))
                .findFirst()
                .orElse(errorCountry);
    }

}
