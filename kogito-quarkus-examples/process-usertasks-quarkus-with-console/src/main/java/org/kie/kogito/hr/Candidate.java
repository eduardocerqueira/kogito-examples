package org.kie.kogito.hr;

public class Candidate {

    private String name;
    private String email;
    private Integer salary;
    private String skills;

    public Candidate() {
    }

    public Candidate(String name, String email, Integer salary, String skills) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
