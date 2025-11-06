package ro.uvt.dp.tests;

import ro.uvt.dp.adapters.BCRExchangeAdapter;
import ro.uvt.dp.external.BCRExchangeService;
import ro.uvt.dp.interfaces.CurrencyConverter;
import ro.uvt.dp.classes.*;

public class AdapterTest {
    public static void main(String[] args) {
        Bank bank = new Bank("BCR");
        CurrencyConverter converter = new BCRExchangeAdapter(new BCRExchangeService());
        bank.setConverter(converter);

        Client client = new Client.Builder("Andrei", "Timisoara")
                .addAccount(new BasicAccountFactory(), new RONInterestCalculator(), "RO001", 5000.0)
                .build();

        bank.addClient(client);

        System.out.println("\n===== Initial Bank State =====");
        System.out.println(bank);

        double converted = bank.convertClientFunds("Andrei", "RON", "EUR");
        System.out.printf("\nConverted total funds: %.2f RON → %.2f EUR%n",
                client.getAccounts().get(0).getAmount(), converted);
    }
}
