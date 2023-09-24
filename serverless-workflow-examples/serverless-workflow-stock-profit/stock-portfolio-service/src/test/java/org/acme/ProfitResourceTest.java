package org.acme;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProfitResourceTest {

    @Test
    void calculateProfit() {
        BigDecimal profit = ProfitResource.calculateProfit(new BigDecimal("12.56"), new BigDecimal("10.00"));
        assertEquals(new BigDecimal("0.26"), profit);
    }
}