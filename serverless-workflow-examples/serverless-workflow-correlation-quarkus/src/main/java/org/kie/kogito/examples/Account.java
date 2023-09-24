package org.kie.kogito.examples;

public class Account {

    private String email;
    private String userId;

    public Account() {
    }

    public Account(String email, String userId) {
        this.email = email;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }
}
