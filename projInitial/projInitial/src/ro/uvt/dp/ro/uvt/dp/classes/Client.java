package ro.uvt.dp.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ro.uvt.dp.interfaces.InterestCalculator;

public class Client {

    private final String name;
    private final String address;
    private final List<Account> accounts;
    public static final int NUMAR_MAX_CONTURI = 5;

    private Client(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.accounts = new ArrayList<>(builder.accounts);
        LoggerConfig.getInstance().logInfo("Client created: " + name + ", address=" + address);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public Account getAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                LoggerConfig.getInstance().logInfo("Account found for client " + name + ": " + accountNumber);
                return acc;
            }
        }
        LoggerConfig.getInstance().logError(
                "Account not found for client " + name + ": " + accountNumber,
                new IllegalArgumentException("Account not found")
        );
        throw new IllegalArgumentException("Account " + accountNumber + " not found!");
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    // ===== Builder Pattern =====
    public static class Builder {
        private final String name;
        private final String address;
        private final List<Account> accounts = new ArrayList<>();

        public Builder(String name, String address) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Client name cannot be null or empty");
            }
            if (address == null || address.isBlank()) {
                throw new IllegalArgumentException("Client address cannot be null or empty");
            }
            this.name = name;
            this.address = address;
            LoggerConfig.getInstance().logInfo("Started building client: " + name);
        }

        public Builder addAccount(AccountFactory factory, InterestCalculator calculator, String accountNumber, double initialAmount) {
            if (accounts.size() >= NUMAR_MAX_CONTURI) {
                LoggerConfig.getInstance().logError(
                        "Cannot add more than " + NUMAR_MAX_CONTURI + " accounts for client " + name,
                        new IllegalStateException("Too many accounts")
                );
                throw new IllegalStateException("Cannot add more than " + NUMAR_MAX_CONTURI + " accounts");
            }

            Account newAccount = factory.createAccount(accountNumber, initialAmount, calculator);
            accounts.add(newAccount);
            LoggerConfig.getInstance().logInfo("Added account " + accountNumber + " for client " + name);
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
