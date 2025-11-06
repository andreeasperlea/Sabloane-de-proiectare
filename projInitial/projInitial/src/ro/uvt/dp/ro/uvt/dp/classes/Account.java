package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.Operations;
import ro.uvt.dp.interfaces.InterestCalculator;

public class Account implements Operations {

    private String accountCode;
    private double amount;
    private InterestCalculator interestCalculator; // Bridge connection

    public Account(String accountCode, double amount, InterestCalculator interestCalculator) {
        this.accountCode = accountCode;
        this.interestCalculator = interestCalculator;
        depose(amount);
        LoggerConfig.getInstance().logInfo(
                "Created new Account: " + accountCode +
                        " with initial amount " + amount +
                        " using " + interestCalculator.getClass().getSimpleName()
        );
    }

    @Override
    public double getTotalAmount() {
        double total = amount + getInterest();
        LoggerConfig.getInstance().logInfo(
                "Total amount for account " + accountCode + " is " + total
        );
        return total;
    }

    @Override
    public double getInterest() {
        double interest = interestCalculator.calculateInterest(amount);
        LoggerConfig.getInstance().logInfo(
                "Calculated interest for account " + accountCode + " = " + interest
        );
        return interest;
    }

    @Override
    public void depose(double suma) {
        if (suma <= 0) {
            LoggerConfig.getInstance().logWarning(
                    "Attempted deposit of invalid amount: " + suma + " in account " + accountCode
            );
            throw new IllegalArgumentException("Suma depusa trebuie sa fie pozitiva!");
        }
        this.amount += suma;
        LoggerConfig.getInstance().logInfo(
                "Deposited " + suma + " into account " + accountCode + ". New balance: " + amount
        );
    }

    @Override
    public void retrieve(double suma) {
        if (suma <= 0) {
            LoggerConfig.getInstance().logWarning(
                    "Attempted withdrawal of invalid amount: " + suma + " in account " + accountCode
            );
            throw new IllegalArgumentException("Suma retrasa trebuie să fie pozitiva!");
        }
        if (suma > amount) {
            LoggerConfig.getInstance().logError(
                    "Insufficient funds for withdrawal of " + suma + " from account " + accountCode,
                    new Exception()
            );
            throw new IllegalStateException("Fonduri insuficiente!");
        }
        this.amount -= suma;
        LoggerConfig.getInstance().logInfo(
                "Withdrew " + suma + " from account " + accountCode + ". Remaining balance: " + amount
        );
    }

    public void transfer(Account destinatar, double suma) {
        if (destinatar == null) {
            LoggerConfig.getInstance().logError(
                    "Transfer failed: destinatar null from account " + accountCode,
                    new Exception()
            );
            throw new IllegalArgumentException("Contul destinatar nu poate fi null!");
        }

        // Different currencies/interest systems can still block transfer logic if needed
        if (!interestCalculator.getClass().equals(destinatar.interestCalculator.getClass())) {
            LoggerConfig.getInstance().logError(
                    "Transfer failed between different interest calculators: "
                            + interestCalculator.getClass().getSimpleName() + " vs "
                            + destinatar.interestCalculator.getClass().getSimpleName(),
                    new Exception()
            );
            throw new IllegalArgumentException("Transferul între conturi cu dobânzi diferite nu este permis!");
        }

        this.retrieve(suma);
        destinatar.depose(suma);
        LoggerConfig.getInstance().logInfo(
                "Transfer successful: " + suma + " from " + this.accountCode + " to " + destinatar.accountCode
        );
    }

    public String getAccountNumber() {
        return accountCode;
    }

    public double getAmount() {
        return amount;
    }

    public InterestCalculator getInterestCalculator() {
        return interestCalculator;
    }

    @Override
    public String toString() {
        String info = "Account{" +
                "code='" + accountCode + '\'' +
                ", amount=" + amount +
                ", interest=" + getInterest() +
                ", calculator=" + interestCalculator.getClass().getSimpleName() +
                '}';
        LoggerConfig.getInstance().logInfo("Account info requested: " + info);
        return info;
    }
}
