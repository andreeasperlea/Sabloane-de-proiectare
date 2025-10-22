package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.Operations;

public class Account implements Operations {

    private String accountCode;
    private double amount;
    private CurrencyType currencyType;

    public Account(String numarCont, double suma, CurrencyType type) {
        this.accountCode = numarCont;
        this.currencyType = type;
        depose(suma);
        LoggerConfig.logInfo("Created new Account: " + accountCode + " with initial amount " + suma + " " + type);
    }

    @Override
    public double getTotalAmount() {
        double total = amount + getInterest();
        LoggerConfig.logInfo("Total amount for account " + accountCode + " is " + total);
        return total;
    }

    @Override
    public double getInterest() {
        double interest = currencyType.calculateDailyInterest(amount);
        LoggerConfig.logInfo("Calculated interest for account " + accountCode + " = " + interest);
        return interest;
    }

    @Override
    public void depose(double suma) {
        if (suma <= 0) {
            LoggerConfig.logWarning("Attempted deposit of invalid amount: " + suma + " in account " + accountCode);
            throw new IllegalArgumentException("Suma depusa trebuie sa fie pozitiva!");
        }
        this.amount += suma;
        LoggerConfig.logInfo("Deposited " + suma + " into account " + accountCode + ". New balance: " + amount);
    }

    @Override
    public void retrieve(double suma) {
        if (suma <= 0) {
            LoggerConfig.logWarning("Attempted withdrawal of invalid amount: " + suma + " in account " + accountCode);
            throw new IllegalArgumentException("Suma retrasa trebuie să fie pozitiva!");
        }
        if (suma > amount) {
            LoggerConfig.logError("Insufficient funds for withdrawal of " + suma + " from account " + accountCode, new Exception());
            throw new IllegalStateException("Fonduri insuficiente!");
        }
        this.amount -= suma;
        LoggerConfig.logInfo("Withdrew " + suma + " from account " + accountCode + ". Remaining balance: " + amount);
    }

    public void transfer(Account destinatar, double suma) {
        if (destinatar == null) {
            LoggerConfig.logError("Transfer failed: destinatar null from account " + accountCode, new Exception());
            throw new IllegalArgumentException("Contul destinatar nu poate fi null!");
        }
        if (this.currencyType != destinatar.currencyType) {
            LoggerConfig.logError("Transfer failed between different currencies: " + this.currencyType + " vs " + destinatar.currencyType, new Exception());
            throw new IllegalArgumentException("Transferul între monede diferite nu este permis!");
        }
        this.retrieve(suma);
        destinatar.depose(suma);
        LoggerConfig.logInfo("Transfer successful: " + suma + " from " + this.accountCode + " to " + destinatar.accountCode);
    }

    public String getAccountNumber() {
        return accountCode;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        String info = "Account{" +
                "code='" + accountCode + '\'' +
                ", amount=" + amount +
                ", currency=" + currencyType +
                ", dailyInterest=" + getInterest() +
                '}';
        LoggerConfig.logInfo("Account info requested: " + info);
        return info;
    }
}
