package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ro.uvt.dp.classes.*;
import ro.uvt.dp.interfaces.InterestCalculator;

public class InvestmentAccountTest {

    @Test
    public void testInvestmentAccountInitialization() {
        InterestCalculator calc = new RONInterestCalculator();
        InvestmentAccount ia = new InvestmentAccount("INV001", 2000.0, calc);

        assertEquals(2000.0, ia.getAmount(), 0.001, "Initial balance should be correctly set");
        assertEquals(calc, ia.getInterestCalculator(), "Interest calculator should match constructor argument");
    }

    @Test
    public void testGetInterestIncludesInvestmentRate() {
        InterestCalculator calc = new RONInterestCalculator();
        InvestmentAccount ia = new InvestmentAccount("INV002", 1000.0, calc);

        double baseInterest = calc.calculateInterest(ia.getAmount());
        double expected = baseInterest + (ia.getAmount() * 0.15 / 100);

        assertEquals(expected, ia.getInterest(), 0.001, "Interest should include 0.15% investment rate");
    }

    @Test
    public void testToStringContainsAccountDetails() {
        InvestmentAccount ia = new InvestmentAccount("INV003", 1500.0, new EURInterestCalculator());
        String result = ia.toString();

        assertTrue(result.contains("InvestmentAccount"), "toString should include class name");
        assertTrue(result.contains("code='INV003'"), "toString should include account code");
        assertTrue(result.contains("amount=1500.0"), "toString should include account amount");
        assertTrue(result.contains("calculator=EURInterestCalculator"), "toString should include interest calculator type");
    }

    @Test
    public void testInvestmentRateConstant() {
        try {
            var field = InvestmentAccount.class.getDeclaredField("INVESTMENT_RATE");
            field.setAccessible(true);
            double value = (double) field.get(null);
            assertEquals(0.15, value, 0.0001, "INVESTMENT_RATE should be 0.15");
        } catch (Exception e) {
            fail("Failed to access INVESTMENT_RATE field");
        }
    }
}
