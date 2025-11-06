package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;

public class InvestmentAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(String accountNumber, double initialAmount, InterestCalculator interestCalculator) {
        LoggerConfig.getInstance().logInfo("Creating investment account " + accountNumber);
        return new InvestmentAccount(accountNumber, initialAmount, interestCalculator);
    }
}
