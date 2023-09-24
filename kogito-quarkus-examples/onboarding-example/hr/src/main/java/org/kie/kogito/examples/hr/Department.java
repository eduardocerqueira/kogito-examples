package org.kie.kogito.examples.hr;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;

public class Department implements RuleUnitData {

    final SingletonStore<Employee> employee = DataSource.createSingleton();

    public SingletonStore<Employee> getEmployee() {
        return employee;
    }
}