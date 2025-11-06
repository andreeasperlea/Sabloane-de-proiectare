package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;

public abstract class AccountFactory {
    public abstract Account createAccount(String accountNumber, double initialAmount, InterestCalculator interestCalculator);
}
