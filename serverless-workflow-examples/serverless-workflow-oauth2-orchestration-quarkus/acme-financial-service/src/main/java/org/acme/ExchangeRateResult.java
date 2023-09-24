package org.acme;

public class ExchangeRateResult {

    private double rate;

    public ExchangeRateResult() {
    }

    public ExchangeRateResult(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
