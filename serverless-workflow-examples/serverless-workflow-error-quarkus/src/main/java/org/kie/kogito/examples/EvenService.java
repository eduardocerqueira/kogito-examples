package org.kie.kogito.examples;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EvenService {

    public void isEven(int number) {
        if (number % 2 != 0) {
            throw new IllegalArgumentException("Odd situation");
        }
    }

    public void isSquare(int number) {
        double sqrt = Math.sqrt(number);
        if (sqrt == Math.round(sqrt)) {
            throw new RuntimeException("Number has a perfect square");
        }
    }
}
