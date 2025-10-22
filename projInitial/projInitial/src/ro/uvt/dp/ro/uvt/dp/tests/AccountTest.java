package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;

import ro.uvt.dp.classes.Account;
import ro.uvt.dp.classes.CurrencyType;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDeposeIncreasesAmount() {
        Account acc = new Account("RO001", 100, CurrencyType.RON);
        acc.depose(50);
        assertEquals(150, acc.getAmount(), 0.001, "Depunerea ar trebui sa creasca soldul");
    }

    @Test
    public void testDeposeNegativeThrowsException() {
        Account acc = new Account("RO001", 100, CurrencyType.RON);
        assertThrows(IllegalArgumentException.class, () -> acc.depose(-20));
    }

    @Test
    public void testRetrieveDecreasesAmount() {
        Account acc = new Account("RO001", 100, CurrencyType.RON);
        acc.retrieve(40);
        assertEquals(60, acc.getAmount(), 0.001);
    }

    @Test
    public void testRetrieveInsufficientFundsThrows() {
        Account acc = new Account("RO001", 50, CurrencyType.RON);
        assertThrows(IllegalStateException.class, () -> acc.retrieve(100));
    }

    @Test
    public void testRetrieveNegativeThrows() {
        Account acc = new Account("RO001", 100, CurrencyType.RON);
        assertThrows(IllegalArgumentException.class, () -> acc.retrieve(-5));
    }

    @Test
    public void testTransferBetweenSameCurrencyAccounts() {
        Account a1 = new Account("RO100", 500, CurrencyType.RON);
        Account a2 = new Account("RO200", 200, CurrencyType.RON);

        a1.transfer(a2, 100);

        assertEquals(400, a1.getAmount(), 0.001);
        assertEquals(300, a2.getAmount(), 0.001);
    }

    @Test
    public void testTransferDifferentCurrenciesThrows() {
        Account a1 = new Account("RO100", 500, CurrencyType.RON);
        Account a2 = new Account("EU200", 200, CurrencyType.EUR);

        assertThrows(IllegalArgumentException.class, () -> a1.transfer(a2, 100));
    }

    @Test
    public void testTransferNegativeAmountThrows() {
        Account a1 = new Account("RO100", 500, CurrencyType.RON);
        Account a2 = new Account("RO200", 200, CurrencyType.RON);

        assertThrows(IllegalArgumentException.class, () -> a1.transfer(a2, -50));
    }

    @Test
    public void testInterestCalculationRON() {
        Account acc = new Account("RO001", 400, CurrencyType.RON);
        assertEquals(0.3, acc.getInterest(), 0.001);
    }

    @Test
    public void testInterestCalculationRONHighAmount() {
        Account acc = new Account("RO001", 600, CurrencyType.RON);
        assertEquals(0.8, acc.getInterest(), 0.001);
    }

    @Test
    public void testInterestCalculationEUR() {
        Account acc = new Account("EU001", 200, CurrencyType.EUR);
        assertEquals(0.1, acc.getInterest(), 0.001);
    }

    @Test
    public void testTotalAmountIncludesDailyInterest() {
        Account acc = new Account("RO001", 1000, CurrencyType.RON);
        double expected = 1000 + acc.getInterest();
        assertEquals(expected, acc.getTotalAmount(), 0.001);
    }
}
