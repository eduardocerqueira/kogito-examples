package org.kie.kogito.examples.springboot;

import java.util.Random;

import org.kie.kogito.examples.springboot.demo.Order;
import org.springframework.stereotype.Component;

@Component
public class CalculationService {

    private Random random = new Random();

    public Order calculateTotal(Order order) {
        order.setTotal(random.nextDouble());

        return order;
    }
}
