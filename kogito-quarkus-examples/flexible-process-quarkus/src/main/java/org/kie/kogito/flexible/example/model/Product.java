package org.kie.kogito.flexible.example.model;

public class Product {

    private String name;
    private String family;

    public Product() {
    }

    public Product(Product product) {
        this.name = product.name;
        this.family = product.family;
    }

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", family='" + family + '\'' + '}';
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Product setFamily(String family) {
        this.family = family;
        return this;
    }
}