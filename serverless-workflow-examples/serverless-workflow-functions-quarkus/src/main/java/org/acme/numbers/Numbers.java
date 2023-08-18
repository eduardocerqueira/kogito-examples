package org.acme.numbers;

import java.util.Collection;
import java.util.Map;

public class Numbers {
    private Collection<Number> numbers;

    private Map<String, Object> additionalData;

    public Collection<Number> getNumbers() {
        return numbers;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }
}
