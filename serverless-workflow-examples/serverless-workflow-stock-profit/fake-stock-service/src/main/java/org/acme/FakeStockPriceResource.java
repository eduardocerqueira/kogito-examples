package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.math.BigDecimal;
import java.util.Map;

@Path("/stock-price")
public class FakeStockPriceResource {

    private final Map<String, BigDecimal> stocks = Map.of(
            "XPTO", BigDecimal.valueOf(10.99),
            "ABCD", BigDecimal.valueOf(30.50),
            "KGTO", BigDecimal.valueOf(75),
            "KIE", BigDecimal.valueOf(92.33)
    );

    @GET
    @Path("/{symbol}")
    public Stock get(@PathParam("symbol") String symbol) {
        BigDecimal price = stocks.get(symbol.toUpperCase());
        if (price != null) {
            return new Stock(symbol, price);
        } else {
            throw new IllegalArgumentException("Unknown stock symbol: " + symbol);
        }
    }
}