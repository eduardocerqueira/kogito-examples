package org.kogito.serverless.examples.functions;

import javax.inject.Inject;

import org.kogito.serverless.examples.input.Country;
import org.kogito.serverless.examples.services.ClassificationService;
import org.kogito.serverless.examples.services.CountriesService;

import io.quarkus.funqy.Funq;

public class ClassificationFunction {
    @Inject
    CountriesService countriesService;

    @Inject
    ClassificationService classificationService;

    @Funq
    public Country classify(Country country) {
        return classificationService.getClassification(
                countriesService.getCountry(country.name));
    }
}
