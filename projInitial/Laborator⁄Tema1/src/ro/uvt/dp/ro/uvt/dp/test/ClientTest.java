package ro.uvt.dp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ro.uvt.dp.*;
import ro.uvt.dp.classes.Account;
import ro.uvt.dp.classes.Client;
import ro.uvt.dp.classes.CurrencyType;

public class ClientTest {

    @Test
    public void testClientInitialization() {
        Client c = new Client("Andrei", "Timisoara", CurrencyType.RON, "RO001", 1000.0);
        assertEquals("Andrei", c.getName());
        assertEquals("Timisoara", c.getAddress());
        assertTrue(c.getAccounts().isEmpty(), "New client should start with 0 accounts");
    }

    @Test
    public void testAddAccountIncreasesList() {
        Client c = new Client("Andrei", "Timisoara", CurrencyType.RON, "RO001", 1000.0);
        c.addAccount(CurrencyType.RON, "RO002", 500.0);
        assertEquals(1, c.getAccounts().size(), "Adding one account should increase list size");
        Account acc = c.getAccounts().get(0);
        assertEquals("RO002", acc.getAccountNumber());
        assertEquals(500.0, acc.getAmount(), 0.001);
    }

    @Test
    public void testAddAccountExceedsMaxThrows() {
        Client c = new Client("Andrei", "Timisoara", CurrencyType.RON, "RO001", 100.0);
        for (int i = 1; i <= Client.NUMAR_MAX_CONTURI; i++) {
            c.addAccount(CurrencyType.RON, "ACC" + i, 100.0);
        }
        assertEquals(Client.NUMAR_MAX_CONTURI, c.getAccounts().size());
        assertThrows(IllegalStateException.class, () -> 
            c.addAccount(CurrencyType.RON, "ACC6", 100.0)
        );
    }

    @Test
    public void testGetAccountReturnsCorrectAccount() {
        Client c = new Client("Andrei", "Timisoara", CurrencyType.RON, "RO001", 100.0);
        c.addAccount(CurrencyType.RON, "RO002", 500.0);
        c.addAccount(CurrencyType.RON, "RO003", 600.0);
        Account a = c.getAccount("RO002");
        assertEquals("RO002", a.getAccountNumber());
        assertEquals(500.0, a.getAmount(), 0.001);
    }

    @Test
    public void testGetAccountNotFoundThrows() {
        Client c = new Client("Andrei", "Timisoara", CurrencyType.RON, "RO001", 100.0);
        assertThrows(IllegalArgumentException.class, () -> 
            c.getAccount("INVALID")
        );
    }

    @Test
    public void testToStringContainsClientInfo() {
        Client c = new Client("Andrei", "Timisoara", CurrencyType.RON, "RO001", 100.0);
        c.addAccount(CurrencyType.RON, "RO002", 500.0);
        String s = c.toString();
        assertTrue(s.contains("Andrei"));
        assertTrue(s.contains("Timisoara"));
        assertTrue(s.contains("RO002"));
    }
}
