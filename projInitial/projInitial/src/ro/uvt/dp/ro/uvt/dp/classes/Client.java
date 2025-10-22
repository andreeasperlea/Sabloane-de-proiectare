package ro.uvt.dp.classes;

import java.util.ArrayList;

public class Client {
    public static final int NUMAR_MAX_CONTURI = 5;

    private String name;
    private String address;
    private ArrayList<Account> accounts;
    private String dateOfBirth;
    private String phone;
    private String email;

    public Client(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.accounts = builder.accounts;
        this.dateOfBirth = builder.dateOfBirth;
        this.phone = builder.phone;
        this.email = builder.email;
        LoggerConfig.getInstance().logInfo("Created new client: " + name + " at address: " + address);
    }

    public static class Builder {
        private String name;
        private String address;
        private String dateOfBirth;
        private String phone;
        private String email;
        private ArrayList<Account> accounts = new ArrayList<>();

        public Builder(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder addAccount(AccountFactory factory, CurrencyType currencyType, String accountNumber, double balance) {
            if (accounts.size() >= Client.NUMAR_MAX_CONTURI) {
                LoggerConfig.getInstance().logWarning("Client " + name + " tried to exceed max account limit (" + Client.NUMAR_MAX_CONTURI + ")");
                throw new IllegalStateException("Numarul maxim de conturi a fost atins");
            }
            Account account = factory.createAccount(accountNumber, balance, currencyType);
            accounts.add(account);
            LoggerConfig.getInstance().logInfo("Added account to builder for " + name + ": " + accountNumber);
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    public Account getAccount(String accountCode) {
        LoggerConfig.getInstance().logInfo("Client " + name + " requested account: " + accountCode);
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountCode)) {
                LoggerConfig.getInstance().logInfo("Account " + accountCode + " found for client " + name);
                return a;
            }
        }
        LoggerConfig.getInstance().logError("Account not found for client " + name + ": " + accountCode, new Exception());
        throw new IllegalArgumentException("Contul cu numarul " + accountCode + " nu a fost gasit.");
    }

    @Override
    public String toString() {
        String info = "Client {" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
        LoggerConfig.getInstance().logInfo("Client info requested: " + info);
        return info;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }
}
