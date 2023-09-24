package org.acme;

public final class StockProfit {

    private final String symbol;

    private final String profit;

    public StockProfit(String symbol, String profit) {
        this.symbol = symbol;
        this.profit = profit;
    }

    public String getProfit() {
        return profit;
    }

    public String getSymbol() {
        return symbol;
    }
}
