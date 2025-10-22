package ro.uvt.dp.classes;

import java.util.ArrayList;

public class Client {
    public static final int NUMAR_MAX_CONTURI = 5;

    private String name;
    private String address;
    private ArrayList<Account> accounts;

    public Client(String nume, String adresa, CurrencyType moneda, String numarCont, double suma) {
        this.name = nume;
        this.address = adresa;
        this.accounts = new ArrayList<>();
        LoggerConfig.logInfo("Created new client: " + name + " at address: " + address);
    }

    public void addAccount(CurrencyType moneda, String numarCont, double suma) {
        if (accounts.size() >= NUMAR_MAX_CONTURI) {
            LoggerConfig.logWarning("Client " + name + " tried to exceed max account limit (" + NUMAR_MAX_CONTURI + ")");
            throw new IllegalStateException("Numarul maxim de conturi a fost atins");
        }
        Account c = createAccount(moneda, numarCont, suma);
        accounts.add(c);
        LoggerConfig.logInfo("Added new account " + numarCont + " (" + moneda + ", " + suma + ") for client " + name);
    }

    private Account createAccount(CurrencyType moneda, String numarCont, double suma) {
        LoggerConfig.logInfo("Creating new account " + numarCont + " for client " + name);
        return new Account(numarCont, suma, moneda);
    }

    public Account getAccount(String accountCode) {
        LoggerConfig.logInfo("Client " + name + " requested account: " + accountCode);
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountCode)) {
                LoggerConfig.logInfo("Account " + accountCode + " found for client " + name);
                return a;
            }
        }
        LoggerConfig.logError("Account not found for client " + name + ": " + accountCode, new Exception());
        throw new IllegalArgumentException("Contul cu numarul " + accountCode + " nu a fost gasit.");
    }

    @Override
    public String toString() {
        String info = "Client {" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
        LoggerConfig.logInfo("Client info requested: " + info);
        return info;
    }

    public String getName() {
        return name;
    }

    public void setName(String nume) {
        this.name = nume;
        LoggerConfig.logInfo("Client name changed to: " + nume);
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
