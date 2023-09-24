package org.kie.kogito.examples.hr;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;

public class EmployeeValidation implements RuleUnitData {

    private final SingletonStore<Employee> employee = DataSource.createSingleton();
    private final SingletonStore<EmployeeValidationModel> validation = DataSource.createSingleton();

    public SingletonStore<Employee> getEmployee() {
        return employee;
    }

    public SingletonStore<EmployeeValidationModel> getValidation() {
        return validation;
    }
}