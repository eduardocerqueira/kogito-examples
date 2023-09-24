package org.kie.kogito.examples.hr;

public class DepartmentModel {

    private org.kie.kogito.examples.hr.Employee employee;

    public org.kie.kogito.examples.hr.Employee getEmployee() {
        return employee;
    }

    public void setEmployee(org.kie.kogito.examples.hr.Employee employee) {
        this.employee = employee;
    }

    private java.lang.String manager;

    public java.lang.String getManager() {
        return manager;
    }

    public void setManager(java.lang.String manager) {
        this.manager = manager;
    }

    private java.lang.String department;

    public java.lang.String getDepartment() {
        return department;
    }

    public void setDepartment(java.lang.String department) {
        this.department = department;
    }
}
