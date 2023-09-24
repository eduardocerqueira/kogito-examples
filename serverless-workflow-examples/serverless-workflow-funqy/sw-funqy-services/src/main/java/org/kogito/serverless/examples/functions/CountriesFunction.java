package org.kogito.serverless.examples.functions;

import javax.inject.Inject;

import org.kogito.serverless.examples.input.Country;
import org.kogito.serverless.examples.services.CountriesService;

import io.quarkus.funqy.Funq;

public class CountriesFunction {
    @Inject
    CountriesService countriesService;

    @Funq
    public Country country(Country country) {
        return countriesService.getCountry(country.name);
    }
}
