package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ro.uvt.dp.classes.*;
import ro.uvt.dp.interfaces.InterestCalculator;

public class AccountTest {

    @Test
    public void testDeposeIncreasesAmount() {
        Account acc = new Account("RO001", 100, new RONInterestCalculator());
        acc.depose(50);
        assertEquals(150, acc.getAmount(), 0.001, "Depunerea ar trebui sa creasca soldul");
    }

    @Test
    public void testDeposeNegativeThrowsException() {
        Account acc = new Account("RO001", 100, new RONInterestCalculator());
        assertThrows(IllegalArgumentException.class, () -> acc.depose(-20));
    }

    @Test
    public void testRetrieveDecreasesAmount() {
        Account acc = new Account("RO001", 100, new RONInterestCalculator());
        acc.retrieve(40);
        assertEquals(60, acc.getAmount(), 0.001);
    }

    @Test
    public void testRetrieveInsufficientFundsThrows() {
        Account acc = new Account("RO001", 50, new RONInterestCalculator());
        assertThrows(IllegalStateException.class, () -> acc.retrieve(100));
    }

    @Test
    public void testRetrieveNegativeThrows() {
        Account acc = new Account("RO001", 100, new RONInterestCalculator());
        assertThrows(IllegalArgumentException.class, () -> acc.retrieve(-5));
    }

    @Test
    public void testTransferBetweenSameInterestCalculators() {
        Account a1 = new Account("RO100", 500, new RONInterestCalculator());
        Account a2 = new Account("RO200", 200, new RONInterestCalculator());

        a1.transfer(a2, 100);

        assertEquals(400, a1.getAmount(), 0.001);
        assertEquals(300, a2.getAmount(), 0.001);
    }

    @Test
    public void testTransferDifferentInterestCalculatorsThrows() {
        Account a1 = new Account("RO100", 500, new RONInterestCalculator());
        Account a2 = new Account("EU200", 200, new EURInterestCalculator());

        assertThrows(IllegalArgumentException.class, () -> a1.transfer(a2, 100));
    }

    @Test
    public void testTransferNegativeAmountThrows() {
        Account a1 = new Account("RO100", 500, new RONInterestCalculator());
        Account a2 = new Account("RO200", 200, new RONInterestCalculator());

        assertThrows(IllegalArgumentException.class, () -> a1.transfer(a2, -50));
    }

    @Test
    public void testInterestCalculationRONLowAmount() {
        InterestCalculator ronCalc = new RONInterestCalculator();
        double interest = ronCalc.calculateInterest(400);
        assertEquals(0.3, interest, 0.001);
    }

    @Test
    public void testInterestCalculationRONHighAmount() {
        InterestCalculator ronCalc = new RONInterestCalculator();
        double interest = ronCalc.calculateInterest(600);
        assertEquals(0.8, interest, 0.001);
    }

    @Test
    public void testInterestCalculationEUR() {
        InterestCalculator eurCalc = new EURInterestCalculator();
        double interest = eurCalc.calculateInterest(200);
        assertEquals(0.1, interest, 0.001);
    }

    @Test
    public void testTotalAmountIncludesDailyInterest() {
        Account acc = new Account("RO001", 1000, new RONInterestCalculator());
        double expected = 1000 + acc.getInterest();
        assertEquals(expected, acc.getTotalAmount(), 0.001);
    }
}
