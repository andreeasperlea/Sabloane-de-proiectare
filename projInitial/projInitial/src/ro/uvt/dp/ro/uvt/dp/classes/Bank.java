package ro.uvt.dp.classes;

import java.util.Arrays;
import ro.uvt.dp.interfaces.CurrencyConverter;

public class Bank {

    private final static int MAX_CLIENTS_NUMBER = 100;
    private final Client[] clients;
    private int clientsNumber;
    private final String bankCode;

    private CurrencyConverter converter;

    public Bank(String codBanca) {
        this.bankCode = codBanca;
        this.clients = new Client[MAX_CLIENTS_NUMBER];
        LoggerConfig.getInstance().logInfo("Bank created with code: " + codBanca);
    }
    public void setConverter(CurrencyConverter converter) {
        if (converter == null) {
            LoggerConfig.getInstance().logWarning("Attempted to set null currency converter for bank " + bankCode);
            throw new IllegalArgumentException("Currency converter cannot be null!");
        }
        this.converter = converter;
        LoggerConfig.getInstance().logInfo("Currency converter set for bank " + bankCode);
    }

    public double convertClientFunds(String clientName, String fromCurrency, String toCurrency) {
        if (converter == null) {
            LoggerConfig.getInstance().logError("No currency converter set for bank " + bankCode, new Exception());
            throw new IllegalStateException("Currency converter not set!");
        }

        Client client = getClient(clientName);
        if (client == null) {
            LoggerConfig.getInstance().logWarning("Client not found for conversion in bank " + bankCode + ": " + clientName);
            throw new IllegalArgumentException("Client not found: " + clientName);
        }
        double totalFunds = 0.0;
        for (Account acc : client.getAccounts()) {
            totalFunds += acc.getAmount();
        }

        double convertedAmount = converter.convert(totalFunds, fromCurrency, toCurrency);
        LoggerConfig.getInstance().logInfo("Converted funds for " + clientName + ": " + totalFunds + " " + fromCurrency + " -> " + convertedAmount + " " + toCurrency);
        return convertedAmount;
    }

    public void addClient(Client c) {
        if (c == null) {
            LoggerConfig.getInstance().logWarning("Attempted to add null client to bank " + bankCode);
            throw new IllegalArgumentException("Client cannot be null!");
        }
        if (clientsNumber >= MAX_CLIENTS_NUMBER) {
            LoggerConfig.getInstance().logError("Bank " + bankCode + " reached maximum number of clients.", new Exception());
            throw new IllegalStateException("Maximum number of clients reached!");
        }

        clients[clientsNumber++] = c;
        LoggerConfig.getInstance().logInfo("Client added to bank " + bankCode + ": " + c.getName());
    }

    public Client getClient(String nume) {
        for (int i = 0; i < clientsNumber; i++) {
            if (clients[i].getName().equals(nume)) {
                LoggerConfig.getInstance().logInfo("Client found in bank " + bankCode + ": " + nume);
                return clients[i];
            }
        }
        LoggerConfig.getInstance().logWarning("Client not found in bank " + bankCode + ": " + nume);
        return null;
    }

    @Override
    public String toString() {
        // avoid printing null clients beyond clientsNumber
        Client[] actualClients = Arrays.copyOf(clients, clientsNumber);
        String info = "Bank [code=" + bankCode + ", clients=" + Arrays.toString(actualClients) + "]";
        LoggerConfig.getInstance().logInfo("Bank info requested: " + info);
        return info;
    }
}
