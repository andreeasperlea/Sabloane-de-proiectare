package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;

public class BasicAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(String accountNumber, double initialAmount, InterestCalculator interestCalculator) {
        LoggerConfig.getInstance().logInfo("Creating basic account " + accountNumber);
        return new Account(accountNumber, initialAmount, interestCalculator);
    }
}
