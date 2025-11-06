package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ro.uvt.dp.classes.*;
import ro.uvt.dp.interfaces.InterestCalculator;

public class SavingsAccountTest {

    @Test
    public void testSavingsAccountInheritsBaseAmount() {
        SavingsAccount sa = new SavingsAccount("SA001", 1000.0, new RONInterestCalculator());
        assertEquals(1000.0, sa.getAmount(), 0.001, "Initial amount should be correctly set");
    }

    @Test
    public void testGetInterestIncludesBonusRate() {
        InterestCalculator calc = new RONInterestCalculator();
        SavingsAccount sa = new SavingsAccount("SA002", 1000.0, calc);

        double baseInterest = calc.calculateInterest(sa.getAmount());
        double expected = baseInterest + sa.getAmount() * SavingsAccount.SAVINGS_BONUS_RATE / 100;

        assertEquals(expected, sa.getInterest(), 0.001, "Interest should include bonus rate");
    }

    @Test
    public void testToStringContainsAccountDetails() {
        SavingsAccount sa = new SavingsAccount("SA003", 500.0, new EURInterestCalculator());
        String result = sa.toString();

        assertTrue(result.contains("SavingsAccount"), "toString should include class name");
        assertTrue(result.contains("code='SA003'"), "toString should include account code");
        assertTrue(result.contains("amount=500.0"), "toString should include account amount");
        assertTrue(result.contains("calculator=EURInterestCalculator"), "toString should include calculator name");
    }

    @Test
    public void testSavingsBonusRateConstant() {
        assertEquals(0.05, SavingsAccount.SAVINGS_BONUS_RATE, 0.0001, "Bonus rate constant should be 0.05");
    }
}
