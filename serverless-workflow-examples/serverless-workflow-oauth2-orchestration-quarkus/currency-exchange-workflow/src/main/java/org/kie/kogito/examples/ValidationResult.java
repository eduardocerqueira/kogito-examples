package org.kie.kogito.examples;

/**
 * Used to return the validation results to the Currency Exchange Workflow.
 */
public class ValidationResult {

    private String executionStatus;
    private String executionStatusMessage;

    public ValidationResult() {
    }

    public ValidationResult(String executionStatus, String executionStatusMessage) {
        this.executionStatus = executionStatus;
        this.executionStatusMessage = executionStatusMessage;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public String getExecutionStatusMessage() {
        return executionStatusMessage;
    }
}
