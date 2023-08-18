package org.acme.travel;

import org.drools.ruleunits.api.*;

public class TravellerValidationService implements RuleUnitData {
    private final SingletonStore<Traveller> traveller = DataSource.createSingleton();

    public SingletonStore<Traveller> getTraveller() {
        return traveller;
    }
}
