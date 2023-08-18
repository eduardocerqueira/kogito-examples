package org.acme;

import java.math.BigDecimal;

public final class Stock {

    private final String symbol;

    private final BigDecimal currentPrice;

    public Stock(String symbol, BigDecimal currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }
}
