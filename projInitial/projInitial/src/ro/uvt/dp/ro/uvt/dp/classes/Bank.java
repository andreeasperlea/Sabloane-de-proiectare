package ro.uvt.dp.classes;

import java.util.Arrays;

public class Bank {

    private final static int MAX_CLIENTS_NUMBER = 100;
    private Client clients[];
    private int clientsNumber;
    private String bankCode;

    public Bank(String codBanca) {
        this.bankCode = codBanca;
        this.clients = new Client[MAX_CLIENTS_NUMBER];
        LoggerConfig.getInstance().logInfo("Bank created with code: " + codBanca);
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
        String info = "Bank [code=" + bankCode + ", clients=" + Arrays.toString(clients) + "]";
        LoggerConfig.getInstance().logInfo("Bank info requested: " + info);
        return info;
    }
}
