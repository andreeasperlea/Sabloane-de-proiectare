package ro.uvt.dp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ro.uvt.dp.*;
import ro.uvt.dp.classes.Account;
import ro.uvt.dp.classes.CurrencyType;
import ro.uvt.dp.classes.SavingsAccount;


public class SavingsAccountTest {

    @Test
    public void testSavingsAccountInheritsBaseAmount() {
        SavingsAccount sa = new SavingsAccount("SA001", 1000.0, CurrencyType.RON);
        assertEquals(1000.0, sa.getAmount(), 0.001, "Initial amount should be correctly set");
    }

    @Test
    public void testGetInterestIncludesBonusRate() {
        SavingsAccount sa = new SavingsAccount("SA002", 1000.0, CurrencyType.RON);
        double expected = sa.getAmount() * SavingsAccount.SAVINGS_BONUS_RATE / 100
                + new Account("A001", 1000.0, CurrencyType.RON).getInterest();
        assertEquals(expected, sa.getInterest(), 0.001, "Interest should include bonus rate");
    }

    @Test
    public void testToStringContainsAccountDetails() {
        SavingsAccount sa = new SavingsAccount("SA003", 500.0, CurrencyType.RON);
        String result = sa.toString();
        assertTrue(result.contains("SavingsAccount"));
        assertTrue(result.contains("code='SA003'"));
        assertTrue(result.contains("amount=500.0"));
        assertTrue(result.contains("currency=RON"));
    }

    @Test
    public void testSavingsBonusRateConstant() {
        assertEquals(0.05, SavingsAccount.SAVINGS_BONUS_RATE, 0.0001, "Bonus rate constant should be 0.05");
    }
}
