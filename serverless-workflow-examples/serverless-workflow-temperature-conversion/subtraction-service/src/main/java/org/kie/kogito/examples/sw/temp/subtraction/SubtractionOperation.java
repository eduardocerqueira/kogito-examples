package org.kie.kogito.examples.sw.temp.subtraction;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * See: <a href="https://en.wikipedia.org/wiki/Subtraction">Subtraction</a>
 */
@RegisterForReflection
public class SubtractionOperation {

    private float leftElement;
    private float rightElement;

    public SubtractionOperation() {
    }

    public SubtractionOperation(final float leftElement, final float rightElement) {
        this.leftElement = leftElement;
        this.rightElement = rightElement;
    }

    public float getLeftElement() {
        return leftElement;
    }

    public void setLeftElement(float leftElement) {
        this.leftElement = leftElement;
    }

    public float getRightElement() {
        return rightElement;
    }

    public void setRightElement(float rightElement) {
        this.rightElement = rightElement;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "leftElement=" + leftElement +
                ", rightElement=" + rightElement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubtractionOperation subtractionOperation = (SubtractionOperation) o;
        return Float.compare(subtractionOperation.leftElement, leftElement) == 0 && Float.compare(subtractionOperation.rightElement, rightElement) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftElement, rightElement);
    }
}
