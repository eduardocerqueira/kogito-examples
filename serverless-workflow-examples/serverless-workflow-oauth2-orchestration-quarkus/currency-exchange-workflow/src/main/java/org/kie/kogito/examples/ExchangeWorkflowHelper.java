package org.kie.kogito.examples;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class used by the Currency Exchange Workflow.
 */
@ApplicationScoped
public class ExchangeWorkflowHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeWorkflowHelper.class);

    /**
     * Naive DB implementation to emulate business related validations.
     * Only the currencies defined in the DB are managed by the Currency Exchange Workflow.
     */
    private static final Set<String> SUPPORTED_CURRENCIES_DB = new LinkedHashSet<>(Arrays.asList("EUR", "USD", "JPY", "GBP", "CAD", "BRL", "AUD"));

    /**
     * Performs the validation of the parameters received by the Currency Exchange Workflow.
     */
    public ValidationResult validateInputs(String currencyFrom, String currencyTo, double amount, String exchangeDate) {
        LOGGER.debug("validateAndInitialize, currencyFrom: {}, currencyTo: {}, amount: {}, exchangeDate: {}",
                currencyFrom, currencyTo, amount, exchangeDate);
        try {
            validateExchangeDate(exchangeDate);
            validateCurrency("currencyFrom", currencyFrom);
            validateCurrency("currencyTo", currencyTo);
        } catch (ValidationException e) {
            return new ValidationResult("ERROR", e.getMessage());
        }
        return new ValidationResult();
    }

    private static void validateExchangeDate(String exchangeDate) throws ValidationException {
        LocalDate date;
        try {
            date = LocalDate.parse(exchangeDate);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid exchangeDate: " + exchangeDate + ", a value in the YYYY-MM-DD must be used");
        }
        LocalDate today = LocalDate.now();
        if (date.isAfter(LocalDate.now())) {
            throw new ValidationException("Invalid exchangeDate: " + exchangeDate + ", a value lower or equal than today: " + today + " must be used");
        }
    }

    private static void validateCurrency(String paramName, String currency) throws ValidationException {
        if (!SUPPORTED_CURRENCIES_DB.contains(currency)) {
            throw new ValidationException("Invalid " + paramName + ": " + currency + ", only the following currencies are supported " + SUPPORTED_CURRENCIES_DB);
        }
    }

    private static class ValidationException extends Exception {

        public ValidationException(String message) {
            super(message);
        }
    }
}
