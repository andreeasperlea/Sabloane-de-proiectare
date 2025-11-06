package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;

public class SavingsAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(String accountNumber, double initialAmount, InterestCalculator interestCalculator) {
        LoggerConfig.getInstance().logInfo("Creating savings account " + accountNumber);
        return new SavingsAccount(accountNumber, initialAmount, interestCalculator);
    }
}
