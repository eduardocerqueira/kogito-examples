package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Map;

@Path("/profit")
public class ProfitResource {

    private final Map<String, BigDecimal> portfolio = Map.of(
            "XPTO", BigDecimal.valueOf(50.25),
            "ABCD", BigDecimal.valueOf(35.80),
            "KGTO", BigDecimal.valueOf(50),
            "KIE", BigDecimal.valueOf(76.89)
    );

    static BigDecimal calculateProfit(BigDecimal currentPrice, BigDecimal previousPrice) {
        return currentPrice.subtract(previousPrice).divide(previousPrice, 2, RoundingMode.HALF_UP);
    }

    @Path("/{symbol}")
    @GET
    public StockProfit getProfit(@PathParam("symbol") String symbol, @QueryParam("currentPrice") BigDecimal currentPrice) {
        BigDecimal portfolioPrice = portfolio.get(symbol.toUpperCase());
        if (portfolioPrice != null) {
            BigDecimal profit = calculateProfit(currentPrice, portfolioPrice);
            return new StockProfit(symbol, NumberFormat.getPercentInstance().format(profit));
        } else {
            throw new IllegalArgumentException("Unknown stock symbol: " + symbol);
        }
    }
}
