package org.kogito.serverless.examples.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    public String name;
    public String capital;
    public String region;
    public String population;
    public String classifier;

    public Country(String name) {
        this.name = name;
    }

    @JsonCreator
    public Country(@JsonProperty("name") String name, @JsonProperty("capital") String capital, @JsonProperty("region") String region) {
        this.name = name;
        this.capital = capital;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getClassifier() {
        return classifier;
    }

    public String getRegion() {
        return region;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

}
