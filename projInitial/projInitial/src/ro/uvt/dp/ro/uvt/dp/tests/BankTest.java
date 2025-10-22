package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ro.uvt.dp.classes.*;

public class BankTest {

    @Test
    public void testBankInitialization() {
        Bank bank = new Bank("B001");
        assertNotNull(bank, "Bank object should be created");
        assertTrue(bank.toString().contains("B001"));
    }

    @Test
    public void testAddClientIncreasesList() {
        Bank bank = new Bank("B001");
        Client c1 = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new BasicAccountFactory(), CurrencyType.RON, "RO001", 100.0)
                .build();
        bank.addClient(c1);

        Client found = bank.getClient("Andrei");
        assertNotNull(found, "Client should be found after being added");
        assertEquals("Andrei", found.getName());
    }

    @Test
    public void testGetClientReturnsNullIfNotFound() {
        Bank bank = new Bank("B002");
        Client c = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new SavingsAccountFactory(), CurrencyType.RON, "RO001", 100.0)
                .build();
        bank.addClient(c);

        assertNull(bank.getClient("Maria"), "Should return null if client not found");
    }

    @Test
    public void testAddMultipleClientsAndRetrieve() {
        Bank bank = new Bank("B003");
        Client c1 = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new BasicAccountFactory(), CurrencyType.RON, "RO001", 100.0)
                .build();
        Client c2 = new Client.Builder("Maria", "Bucuresti")
                .addAccount(new InvestmentAccountFactory(), CurrencyType.EUR, "EU001", 200.0)
                .build();

        bank.addClient(c1);
        bank.addClient(c2);

        assertEquals("Maria", bank.getClient("Maria").getName());
        assertEquals("Andrei", bank.getClient("Andrei").getName());
    }

    @Test
    public void testToStringContainsClientInfo() {
        Bank bank = new Bank("B004");
        Client c1 = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new SavingsAccountFactory(), CurrencyType.RON, "RO001", 100.0)
                .build();
        bank.addClient(c1);

        String result = bank.toString();
        assertTrue(result.contains("Andrei"));
        assertTrue(result.contains("B004"));
    }
}
