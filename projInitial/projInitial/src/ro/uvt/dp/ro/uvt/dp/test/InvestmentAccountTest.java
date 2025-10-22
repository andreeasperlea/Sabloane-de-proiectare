package ro.uvt.dp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ro.uvt.dp.*;
import ro.uvt.dp.classes.Account;
import ro.uvt.dp.classes.CurrencyType;
import ro.uvt.dp.classes.InvestmentAccount;

public class InvestmentAccountTest {

    @Test
    public void testInvestmentAccountInitialization() {
        InvestmentAccount ia = new InvestmentAccount("INV001", 2000.0, CurrencyType.RON);
        assertEquals(2000.0, ia.getAmount(), 0.001, "Initial balance should be correctly set");
        assertEquals(CurrencyType.RON, ia.getCurrencyType(), "Currency should match constructor argument");
    }

    @Test
    public void testGetInterestIncludesInvestmentRate() {
        InvestmentAccount ia = new InvestmentAccount("INV002", 1000.0, CurrencyType.RON);
        double baseInterest = new Account("A001", 1000.0, CurrencyType.RON).getInterest();
        double expected = baseInterest + (ia.getAmount() * 0.15 / 100);
        assertEquals(expected, ia.getInterest(), 0.001, "Interest should include 0.15% investment rate");
    }

    @Test
    public void testToStringContainsAccountDetails() {
        InvestmentAccount ia = new InvestmentAccount("INV003", 1500.0, CurrencyType.RON);
        String result = ia.toString();
        assertTrue(result.contains("InvestmentAccount"));
        assertTrue(result.contains("code='INV003'"));
        assertTrue(result.contains("amount=1500.0"));
        assertTrue(result.contains("currency=RON"));
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
