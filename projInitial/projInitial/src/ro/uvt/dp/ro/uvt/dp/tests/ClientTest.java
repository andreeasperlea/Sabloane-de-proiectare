package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ro.uvt.dp.classes.*;

public class ClientTest {

    @Test
    public void testClientInitialization() {
        Client c = new Client.Builder("Andrei", "Timisoara")
                .build();

        assertEquals("Andrei", c.getName());
        assertEquals("Timisoara", c.getAddress());
        assertTrue(c.getAccounts().isEmpty(), "New client should start with 0 accounts");
    }

    @Test
    public void testAddAccountIncreasesList() {
        Client c = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new BasicAccountFactory(), CurrencyType.RON, "RO002", 500.0)
                .build();

        assertEquals(1, c.getAccounts().size(), "Adding one account should increase list size");
        Account acc = c.getAccounts().get(0);
        assertEquals("RO002", acc.getAccountNumber());
        assertEquals(500.0, acc.getAmount(), 0.001);
    }

    @Test
    public void testAddAccountExceedsMaxThrows() {
        Client.Builder builder = new Client.Builder("Andrei", "Timisoara");
        for (int i = 1; i <= Client.NUMAR_MAX_CONTURI; i++) {
            builder.addAccount(new BasicAccountFactory(), CurrencyType.RON, "ACC" + i, 100.0);
        }
        assertEquals(Client.NUMAR_MAX_CONTURI, builder.build().getAccounts().size());

        assertThrows(IllegalStateException.class,
            () -> builder.addAccount(new BasicAccountFactory(), CurrencyType.RON, "ACC6", 100.0));
    }

    @Test
    public void testGetAccountReturnsCorrectAccount() {
        Client c = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new SavingsAccountFactory(), CurrencyType.RON, "RO002", 500.0)
                .addAccount(new InvestmentAccountFactory(), CurrencyType.RON, "RO003", 600.0)
                .build();

        Account a = c.getAccount("RO002");
        assertEquals("RO002", a.getAccountNumber());
        assertEquals(500.0, a.getAmount(), 0.001);
    }

    @Test
    public void testGetAccountNotFoundThrows() {
        Client c = new Client.Builder("Andrei", "Timisoara")
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                c.getAccount("INVALID"));
    }

    @Test
    public void testToStringContainsClientInfo() {
        Client c = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new InvestmentAccountFactory(), CurrencyType.RON, "RO002", 500.0)
                .build();

        String s = c.toString();
        assertTrue(s.contains("Andrei"));
        assertTrue(s.contains("Timisoara"));
        assertTrue(s.contains("RO002"));
    }
}
