package org.kogito.serverless.examples.functions;

import javax.inject.Inject;

import org.kogito.serverless.examples.input.Country;
import org.kogito.serverless.examples.services.CountriesService;
import org.kogito.serverless.examples.services.PopulationService;

import io.quarkus.funqy.Funq;

public class PopulationFunction {
    @Inject
    CountriesService countriesService;

    @Inject
    PopulationService populationService;

    @Funq
    public Country population(Country country) {
        return populationService.getPopulation(
                countriesService.getCountry(country.name));
    }
}
