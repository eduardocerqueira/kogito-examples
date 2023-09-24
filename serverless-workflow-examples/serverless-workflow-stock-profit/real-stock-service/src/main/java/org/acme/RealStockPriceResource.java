package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;

@Path("/stock-price")
public class RealStockPriceResource {

    public final SecureRandom SECURE_RANDOM = new SecureRandom();

    @GET
    @Path("/{symbol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Stock get(@PathParam("symbol") String symbol) {
        // Emulates a stock variation
        float randomFloat = SECURE_RANDOM.nextFloat();
        BigDecimal price = BigDecimal.valueOf(randomFloat).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        return new Stock(symbol, price);
    }
}