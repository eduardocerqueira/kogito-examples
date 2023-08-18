package org.kie.kogito.examples.sw.temp.multiplication;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * See: <a href="https://en.wikipedia.org/wiki/Multiplication">Multiplication</a>
 */
@RegisterForReflection
public class MultiplicationOperation {

    private float leftElement;
    private float rightElement;

    public MultiplicationOperation() {
    }

    public MultiplicationOperation(final float leftElement, final float rightElement) {
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
        MultiplicationOperation operation = (MultiplicationOperation) o;
        return Float.compare(operation.leftElement, leftElement) == 0 && Float.compare(operation.rightElement, rightElement) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftElement, rightElement);
    }
}
