package org.kie.kogito.examples.sw.orders.processing;

import java.util.Objects;

/**
 * Simple POJO to hold the values for our test data
 */
public class Order {

    private String id;
    private String country;
    private int total;
    private String description;
    private boolean fraudEvaluation;
    private String shipping;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFraudEvaluation() {
        return fraudEvaluation;
    }

    public void setFraudEvaluation(boolean fraudEvaluation) {
        this.fraudEvaluation = fraudEvaluation;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
